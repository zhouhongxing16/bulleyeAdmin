package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.mapper.AttachFilesMapper;
import com.chris.bulleyeadmin.common.pojo.AttachFiles;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.Enums.WxMaterialEnum;
import com.chris.bulleyeadmin.wechat.KefuBuilder.KefuNewsBuilder;
import com.chris.bulleyeadmin.wechat.builder.NewsBuilder;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMaterialMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class WxMaterialService extends BaseService<WxMaterial> {

    @Autowired
    private WxMaterialMapper wxMaterialMapper;
    @Autowired
    private WxAccountMapper wxAccountMapper;
    @Autowired
    private AttachFilesMapper attachFilesMapper;
    @Autowired
    private final WxMpService wxService;

    @Override
    public BaseMapper<WxMaterial> getMapper() {
        return wxMaterialMapper;
    }

    //生成永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult materialUpload(String id) {
        WxMaterial queryWxMaterial = new WxMaterial();
        queryWxMaterial.setId(id);
        //获取父级
        WxMaterial wxMaterial = wxMaterialMapper.selectOne(queryWxMaterial);
        //获取相关接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setSourceId(wxMaterial.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());
        //处理图文消息
        if(WxMaterialEnum.news.toString().equals(wxMaterial.getType())){
            WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();

            WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
            article.setAuthor(wxMaterial.getAuthor());
            article.setContent(wxMaterial.getContent());
            article.setContentSourceUrl(wxMaterial.getContentSourceUrl());
            article.setDigest(wxMaterial.getDigest());
            article.setNeedOpenComment(wxMaterial.getNeedOpenComment());
            article.setOnlyFansCanComment(wxMaterial.getOnlyFansCanComment());
            article.setShowCoverPic(wxMaterial.getNeedOpenComment());

            //封面图片素材id，此处必须进行上传为临时素材处理
            try {
                AttachFiles attachFiles = new AttachFiles();
                attachFiles.setId(wxMaterial.getThumbFileId());
                attachFiles = attachFilesMapper.selectOne(attachFiles);
                if (attachFiles!=null){
                    File dir = new File("G:/projectTemp/"+attachFiles.getPath());
                    WxMpMaterial img = new WxMpMaterial();
                    img.setFile(dir);
                    WxMpMaterialUploadResult wxMpMaterialUploadResult = wxService.getMaterialService().materialFileUpload("image", img);
                    System.out.println("getMediaId="+wxMpMaterialUploadResult.getMediaId());
                    article.setThumbMediaId(wxMpMaterialUploadResult.getMediaId());
                }
            } catch (Exception e) {
                return new JsonResult(false,null,"生成失败，未找到封面图",0, HttpStatus.OK.value());
            }

            article.setThumbUrl(wxMaterial.getDownUrl());
            article.setTitle(wxMaterial.getTitle());
            article.setUrl(wxMaterial.getDownUrl());

            wxMpMaterialNews.addArticle(article);

            try {
                WxMpMaterialUploadResult result = wxService.getMaterialService().materialNewsUpload(wxMpMaterialNews);
                String msg = result.getMediaId()!=null?"生成成功":"生成失败！";
                //记录返回的media_id
                if(result.getMediaId()!=null){
                    wxMaterial.setMediaId(result.getMediaId());
                    wxMaterialMapper.updateByPrimaryKey(wxMaterial);
                }
                return new JsonResult(result.getMediaId()!=null?true:false,null,msg,result.getErrCode(), HttpStatus.OK.value());
            } catch (Exception e) {
                e.printStackTrace();
                return new JsonResult(false,null,"生成失败！",null, HttpStatus.OK.value());
            }

        }else{
            //其他素材（暂时不可用，上传的文件未处理）
            AttachFiles attachFiles = new AttachFiles();
            attachFiles.setId(wxMaterial.getThumbFileId());
            attachFiles = attachFilesMapper.selectOne(attachFiles);
            File dir = new File(attachFiles.getPath());

            WxMpMaterial wxMpMaterial =  new WxMpMaterial();
            wxMpMaterial.setName(wxMaterial.getName());
            wxMpMaterial.setFile(dir);
            //视频素材需要另外处理
            if(WxMaterialEnum.video.toString().equals(wxMaterial.getType())){
                wxMpMaterial.setVideoTitle(wxMaterial.getTitle());
                wxMpMaterial.setVideoIntroduction(wxMaterial.getIntroduction());
            }

            try {
                WxMpMaterialUploadResult result = wxService.getMaterialService().materialFileUpload(wxMaterial.getType(), wxMpMaterial);
                String msg = result.getMediaId()!=null?"生成成功":"生成失败！";
                //记录返回的media_id
                if(result.getMediaId()!=null){
                    wxMaterial.setMediaId(result.getMediaId());
                    wxMaterialMapper.updateByPrimaryKey(wxMaterial);
                }
                return new JsonResult(result.getMediaId()!=null?true:false,null,msg,result.getErrCode(), HttpStatus.OK.value());
            } catch (WxErrorException e) {
                e.printStackTrace();
                return new JsonResult(false,null,"生成失败！",null, HttpStatus.OK.value());
            }
        }

    }

    //修改永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult updateMaterial(WxMaterial new_wxMaterial) {
        /*WxMaterial QwxMaterial = new WxMaterial();
        QwxMaterial.setId(new_wxMaterial.getId());

        WxMaterial old_wxMaterial = wxMaterialMapper.selectOne(QwxMaterial);
        if (!old_wxMaterial.getThumbFileId().equals(new_wxMaterial.getThumbFileId())) {
            JsonResult jsonResult = uploadFile(new_wxMaterial.getThumbFileId());
            if (jsonResult.isSuccess()) {
                new_wxMaterial.setThumbFileId(jsonResult.getData().toString());
            }
        }

        //获取相关接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setSourceId(new_wxMaterial.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());

        WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate = new W

        try {
            boolean flag = wxService.getMaterialService().materialNewsUpdate(new_wxMaterial);
            String msg = flag?"删除成功":"删除失败！";
            return new JsonResult(flag?true:false,null,msg, null, HttpStatus.OK.value());
        } catch (WxErrorException e){
            return new JsonResult(false,null,"修改失败！", null, HttpStatus.OK.value());
        }*/

        return null;
    }

    //删除永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult materialDelete(String id){
        WxMaterial QwxMaterial = new WxMaterial();
        QwxMaterial.setId(id);

        WxMaterial wxMaterial = wxMaterialMapper.selectOne(QwxMaterial);
        //获取相关接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setSourceId(wxMaterial.getSourceId());

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());
        try {
            boolean flag = wxService.getMaterialService().materialDelete(wxMaterial.getMediaId());
            if (flag) {
                wxMaterial.setMediaId("");
                wxMaterialMapper.updateByPrimaryKey(wxMaterial);
            }
            String msg = flag?"删除成功":"删除失败！";
            return new JsonResult(flag?true:false,null,msg, null, HttpStatus.OK.value());
        } catch (WxErrorException e){
            return new JsonResult(false,null,"删除失败！", null, HttpStatus.OK.value());
        }
    }

    //推送永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult pubMaterialToUser(String id){
        //获取素材
        WxMaterial qwxMaterial = new WxMaterial();
        qwxMaterial.setId(id);
        WxMaterial wxMaterial = wxMaterialMapper.selectOne(qwxMaterial);
        //获取相关公众号及接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(wxMaterial.getSourceId());
        WxAccount account = wxAccountMapper.selectOne(queryAccount);
        WxMpService wxService = this.wxService.switchoverTo(account.getAppId());
        try {
            //客服消息获取需要推送的用户openid
            /*String openid = "o49sjv02N1-r-vfq_9EMOcj5hQCY";
            KefuNewsBuilder kefuNewsBuilder = new KefuNewsBuilder();
            boolean flag = kefuNewsBuilder.pubMaterialToUserByKf(wxService, openid, wxMaterial.getMediaId());*/

            //素材群发
            NewsBuilder newsBuilder = new NewsBuilder();
            boolean flag = newsBuilder.pubMaterialToUserAll(wxMaterial, wxService);

            //根据openid发送
            /*List<String> openids = new ArrayList<>();
            openids.add("o49sjv0_iBKGZ5YTeqLiBLMOYFyI");
            openids.add("o49sjv02N1-r-vfq_9EMOcj5hQCY\t");

            NewsBuilder newsBuilder = new NewsBuilder();
            boolean flag = newsBuilder.pubMaterialToUserList(wxMaterial, openids, wxService);*/

            String msg = flag?"推送成功":"推送失败！";
            return new JsonResult(flag?true:false,null,msg, null, HttpStatus.OK.value());
        }catch (Exception e){
            return new JsonResult(false,null,"推送失败", null, HttpStatus.OK.value());
        }
    }

    //处理封面图上传为素材
    private JsonResult uploadFile(String thumbFileId){
        //封面图片素材id，此处必须进行上传为临时素材处理
        try {
            AttachFiles attachFiles = new AttachFiles();
            attachFiles.setId(thumbFileId);
            attachFiles = attachFilesMapper.selectOne(attachFiles);
            if (attachFiles!=null){
                File dir = new File("G:/projectTemp/"+attachFiles.getPath());
                WxMpMaterial img = new WxMpMaterial();
                img.setFile(dir);
                WxMpMaterialUploadResult wxMpMaterialUploadResult = wxService.getMaterialService().materialFileUpload("image", img);
                System.out.println("getMediaId="+wxMpMaterialUploadResult.getMediaId());
                return new JsonResult(true, wxMpMaterialUploadResult.getMediaId(), "生成成功",0, HttpStatus.OK.value());
            }
        } catch (Exception e) {
            return new JsonResult(false,null,"生成失败，未找到封面图",0, HttpStatus.OK.value());
        }
        return null;
    }
}


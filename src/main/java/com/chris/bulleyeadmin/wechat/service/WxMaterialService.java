package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.Enums.WxMaterialEnum;
import com.chris.bulleyeadmin.wechat.KefuBuilder.KefuNewsBuilder;
import com.chris.bulleyeadmin.wechat.config.WxMpConfiguration;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMaterialMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.material.WxMediaImgUploadResult;
import me.chanjar.weixin.mp.bean.material.WxMpMaterial;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialNews;
import me.chanjar.weixin.mp.bean.material.WxMpMaterialUploadResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.List;


@Service
public class WxMaterialService extends BaseService<WxMaterial> {

    @Autowired
    private WxMaterialMapper wxMaterialMapper;
    @Autowired
    private WxAccountMapper wxAccountMapper;

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
        queryaccount.setId(wxMaterial.getAccountId());

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = WxMpConfiguration.getMpServices().get(account.getAppId());
        //处理图文消息
        if(WxMaterialEnum.news.toString().equals(wxMaterial.getType())){
            WxMaterial queryWxMaterial2 = new WxMaterial();
            queryWxMaterial2.setParentId(wxMaterial.getId());
            List<WxMaterial> materialList = wxMaterialMapper.select(queryWxMaterial2);

            WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
            for (WxMaterial item : materialList) {
                WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
                article.setAuthor(item.getAuthor());
                article.setContent(item.getContent());
                article.setContentSourceUrl(item.getContentSourceUrl());
                article.setDigest(item.getDigest());
                article.setNeedOpenComment(item.getNeedOpenComment());
                article.setOnlyFansCanComment(item.getOnlyFansCanComment());
                article.setShowCoverPic(item.getNeedOpenComment());

                //封面图片素材id，此处必须进行上传为临时素材处理
                try {
                    File dir = new File("C:\\Users\\lenovo\\Desktop\\QQ图片20190305142342.jpg");
                    WxMpMaterial img = new WxMpMaterial();
                    img.setFile(dir);
                    WxMpMaterialUploadResult wxMpMaterialUploadResult = wxService.getMaterialService().materialFileUpload("image", img);
                    System.out.println("getMediaId="+wxMpMaterialUploadResult.getMediaId());
                    article.setThumbMediaId(wxMpMaterialUploadResult.getMediaId());
                } catch (Exception e) {
                    return new JsonResult(false,null,"生成失败",0, HttpStatus.OK.value());
                }

                article.setThumbUrl(item.getDownUrl());
                article.setTitle(item.getTitle());
                article.setUrl(item.getDownUrl());

                wxMpMaterialNews.addArticle(article);

            }

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
            //其他素材
            File dir = new File("C:\\Users\\lenovo\\Desktop\\QQ图片20190305142342.jpg");
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

    //删除永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult materialDelete(String id){
        WxMaterial QwxMaterial = new WxMaterial();
        QwxMaterial.setId(id);

        WxMaterial wxMaterial = wxMaterialMapper.selectOne(QwxMaterial);
        //获取相关接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setId(wxMaterial.getAccountId());

        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = WxMpConfiguration.getMpServices().get(account.getAppId());
        try {
            boolean flag = wxService.getMaterialService().materialDelete(wxMaterial.getMediaId());
            String msg = flag?"删除成功":"删除失败！";
            return new JsonResult(flag?true:false,null,msg, null, HttpStatus.OK.value());
        }catch (WxErrorException e){

        }
        return null;
    }

    //推送永久素材
    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult pubMaterialToUser(String id){
        //获取素材
        WxMaterial QwxMaterial = new WxMaterial();
        QwxMaterial.setId(id);
        WxMaterial wxMaterial = wxMaterialMapper.selectOne(QwxMaterial);
        //获取相关公众号及接口
        WxAccount queryaccount = new WxAccount();
        queryaccount.setId(wxMaterial.getAccountId());
        WxAccount account = wxAccountMapper.selectOne(queryaccount);
        WxMpService wxService = WxMpConfiguration.getMpServices().get(account.getAppId());
        try {
            KefuNewsBuilder kefuNewsBuilder = new KefuNewsBuilder();
            boolean flag = kefuNewsBuilder.pubMaterialToUser(wxService, null, wxMaterial.getMediaId());
            String msg = flag?"推送成功":"推送失败！";
            return new JsonResult(flag?true:false,null,msg, null, HttpStatus.OK.value());
        }catch (Exception e){
            return new JsonResult(false,null,"推送失败", null, HttpStatus.OK.value());
        }
    }
}

package com.chris.bulleyeadmin.wechat.service;

import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.Enums.WxMaterialEnum;
import com.chris.bulleyeadmin.wechat.config.WxMpConfiguration;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMaterialMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
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

    @Transactional(propagation = Propagation.REQUIRED)
    public JsonResult materialUpload(String id){
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
        if(WxMaterialEnum.news.equals(wxMaterial.getType())){
            WxMaterial queryWxMaterial2 = new WxMaterial();
            queryWxMaterial2.setParentId(wxMaterial.getId());
            List<WxMaterial> materialList = wxMaterialMapper.select(queryWxMaterial2);

            WxMpMaterialNews wxMpMaterialNews = new WxMpMaterialNews();
            materialList.stream().forEach(item ->{
                WxMpMaterialNews.WxMpMaterialNewsArticle article = new WxMpMaterialNews.WxMpMaterialNewsArticle();
                article.setAuthor(item.getAuthor());
                article.setContent(item.getContent());
                article.setContentSourceUrl(item.getContentSourceUrl());
                article.setDigest(item.getDigest());
                article.setNeedOpenComment(item.getNeedOpenComment());
                article.setOnlyFansCanComment(item.getOnlyFansCanComment());
                article.setShowCoverPic(item.getNeedOpenComment());
                article.setThumbMediaId(item.getThumbMediaId());
                article.setThumbUrl(item.getDownUrl());
                article.setTitle(item.getTitle());
                article.setUrl(item.getDownUrl());

                wxMpMaterialNews.addArticle(article);

            });

            try {
                WxMpMaterialUploadResult result = wxService.getMaterialService().materialNewsUpload(wxMpMaterialNews);
                String msg = result.getErrCode()==0?"新增成功":"新增失败！";
                return new JsonResult(result.getErrCode()==0?true:false,null,msg,result.getErrCode(), HttpStatus.OK.value());
            } catch (WxErrorException e) {
                e.printStackTrace();
                return new JsonResult(false,null,"新增失败！",null, HttpStatus.OK.value());
            }

        }else{
            //处理非图文消息
            WxMpMaterial wxMpMaterial =  new WxMpMaterial();
            wxMpMaterial.setName(wxMaterial.getName());
            wxMpMaterial.setFile(null);
            if(WxMaterialEnum.video.equals(wxMaterial.getType())){
                wxMpMaterial.setVideoTitle(wxMaterial.getTitle());
                wxMpMaterial.setVideoIntroduction(wxMaterial.getIntroduction());
            }

            try {
                WxMpMaterialUploadResult result = wxService.getMaterialService().materialFileUpload(wxMaterial.getType(), wxMpMaterial);
                String msg = result.getErrCode()==0?"新增成功":"新增失败！";
                return new JsonResult(result.getErrCode()==0?true:false,null,msg,result.getErrCode(), HttpStatus.OK.value());
            } catch (WxErrorException e) {
                e.printStackTrace();
                return new JsonResult(false,null,"新增失败！",null, HttpStatus.OK.value());
            }
        }

    }

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
}

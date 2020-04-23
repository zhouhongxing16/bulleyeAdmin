package com.chris.bulleyeadmin.wechat.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.WxMpMassOpenIdsMessage;
import me.chanjar.weixin.mp.bean.WxMpMassTagMessage;
import me.chanjar.weixin.mp.bean.message.*;
import me.chanjar.weixin.mp.bean.result.WxMpMassSendResult;

import java.util.List;


public class NewsBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutNewsMessage.Item item = JSON.parseObject(content, WxMpXmlOutNewsMessage.Item.class);
        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .addArticle(item)
                .build();
        return m;
    }

    public boolean pubMaterialToUserList (WxMaterial wxMaterial, List<String> openids, WxMpService service){
        WxMpMassOpenIdsMessage m = new WxMpMassOpenIdsMessage();
        m.setMediaId(wxMaterial.getMediaId());
        m.setSendIgnoreReprint(true);
        m.setMsgType("mpnews");
        m.setToUsers(openids);

        boolean flag = false;
        try {
            WxMpMassSendResult result = service.getMassMessageService().massOpenIdsMessageSend(m);
            if (result.getErrorCode().equals("0")){
                flag = true;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public boolean pubMaterialToUserAll (WxMaterial wxMaterial, WxMpService service){
        WxMpMassTagMessage m = new WxMpMassTagMessage();
        m.setMediaId(wxMaterial.getMediaId());
        m.setMsgType("mpnews");
        m.setSendAll(true);

        boolean flag = false;
        try {
            WxMpMassSendResult result = service.getMassMessageService().massGroupMessageSend(m);
            if (result.getErrorCode().equals("0")){
                flag = true;
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return flag;
    }

}

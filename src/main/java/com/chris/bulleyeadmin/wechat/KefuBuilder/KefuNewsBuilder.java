package com.chris.bulleyeadmin.wechat.KefuBuilder;

import com.alibaba.fastjson2.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

import java.util.List;

public class KefuNewsBuilder extends KefuAbstractBuilder {

    @Override
    public WxMpKefuMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpKefuMessage.WxArticle article1 = JSONObject.parseObject(content, WxMpKefuMessage.WxArticle.class);
        WxMpKefuMessage m = WxMpKefuMessage.NEWS()
                .toUser(wxMessage.getFromUser())
                .addArticle(article1)
                .build();
        try {
            boolean flag =  service.getKefuService().sendKefuMessage(m);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return m;
    }

    //向指定的用户发送素材
    public boolean pubMaterialToUserByKf(WxMpService service, String openid, String media_id) {
        WxMpKefuMessage m = WxMpKefuMessage.MPNEWS()
                .toUser(openid)
                .mediaId(media_id)
                .build();
        boolean flag = false;
        try {
            flag =  service.getKefuService().sendKefuMessage(m);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return flag;
    }
}

package com.chris.bulleyeadmin.wechat.KefuBuilder;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;

public class KefuNewsBuilder extends KefuAbstractBuilder {

    @Override
    public WxMpKefuMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpKefuMessage.WxArticle article1 = new JSONObject().parseObject(content, WxMpKefuMessage.WxArticle.class);
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
}

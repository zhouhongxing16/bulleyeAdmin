package com.chris.bulleyeadmin.wechat.builder;

import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.*;


public class NewsBuider extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        WxMpXmlOutNewsMessage.Item item = new JSONObject().parseObject(content, WxMpXmlOutNewsMessage.Item.class);
        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .addArticle(item)
                .build();
        return m;
    }
}

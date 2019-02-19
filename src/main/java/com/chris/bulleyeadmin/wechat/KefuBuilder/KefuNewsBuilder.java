package com.chris.bulleyeadmin.wechat.KefuBuilder;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.wechat.builder.AbstractBuilder;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;

public class KefuNewsBuilder extends KefuAbstractBuilder {
    @Override
    public WxMpKefuMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        //WxMpKefuMessage.WxArticle article1 = new JSONObject().parseObject(content, WxMpKefuMessage.WxArticle.class);

        WxMpKefuMessage.WxArticle article1 = new WxMpKefuMessage.WxArticle();
        article1.setUrl("URL");
        article1.setPicUrl("http://g.hiphotos.baidu.com/zhidao/pic/item/0d338744ebf81a4cafdf9769d72a6059242da6a5.jpg");
        article1.setDescription("Is Really A Happy Day");
        article1.setTitle("Happy Day");

        WxMpKefuMessage m = WxMpKefuMessage.NEWS()
                .toUser(wxMessage.getFromUser())
                .addArticle(article1)
                .build();
        return m;
    }
}

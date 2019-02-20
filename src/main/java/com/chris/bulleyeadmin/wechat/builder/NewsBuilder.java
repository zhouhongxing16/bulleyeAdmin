package com.chris.bulleyeadmin.wechat.builder;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.*;

import java.util.List;


public class NewsBuilder extends AbstractBuilder {

    @Override
    public WxMpXmlOutMessage build(String content, WxMpXmlMessage wxMessage, WxMpService service) {
        List<WxMpXmlOutNewsMessage.Item> items = JSON.parseArray(content, WxMpXmlOutNewsMessage.Item.class);
        WxMpXmlOutNewsMessage m = WxMpXmlOutMessage.NEWS()
                .fromUser(wxMessage.getToUser())
                .toUser(wxMessage.getFromUser())
                .articles(items)
                .build();
        return m;
    }
}

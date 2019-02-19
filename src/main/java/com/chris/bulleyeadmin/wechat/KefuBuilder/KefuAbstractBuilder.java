package com.chris.bulleyeadmin.wechat.KefuBuilder;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.kefu.WxMpKefuMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class KefuAbstractBuilder {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    public abstract WxMpKefuMessage build(String content,
                                          WxMpXmlMessage wxMessage, WxMpService service);
}

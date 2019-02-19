package com.chris.bulleyeadmin.wechat.handler;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.wechat.KefuBuilder.KefuNewsBuilder;
import com.chris.bulleyeadmin.wechat.builder.NewsBuider;
import com.chris.bulleyeadmin.wechat.builder.TextBuilder;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;
import com.chris.bulleyeadmin.wechat.service.WxReplyService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

import static me.chanjar.weixin.common.api.WxConsts.XmlMsgType;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class MsgHandler extends AbstractHandler {

    @Autowired
    WxReplyService wxReplyService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服

        WxReply reply = new WxReply();
        reply.setAccountId(wxMessage.getToUser());
        reply.setKeyword(wxMessage.getContent());
        try {
            List<WxReply> replyList = wxReplyService.selectlist(reply);
            for(WxReply reply1:replyList){
                if ("1".equals(reply1.getType())) {
                    return new TextBuilder().build(reply1.getContent(), wxMessage, weixinService);
                }else if("2".equals(reply1.getType())){
                    WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
                    item.setTitle(reply1.getTitle());
                    item.setDescription(reply1.getContent());
                    item.setPicUrl(reply1.getPic());
                    item.setUrl(reply1.getUrl());
                    JSONObject json = JSONObject.parseObject(JSONObject.toJSONString(item));
                    return new NewsBuider().build(json.toString(), wxMessage,weixinService);
                }
            }
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }

}

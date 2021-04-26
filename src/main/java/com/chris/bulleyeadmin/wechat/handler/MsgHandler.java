package com.chris.bulleyeadmin.wechat.handler;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.wechat.KefuBuilder.KefuNewsBuilder;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import com.chris.bulleyeadmin.wechat.utils.TulingApiUtil;
import com.chris.bulleyeadmin.wechat.builder.NewsBuilder;
import com.chris.bulleyeadmin.wechat.builder.TextBuilder;
import com.chris.bulleyeadmin.wechat.pojo.WxConstants;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;
import com.chris.bulleyeadmin.wechat.service.WxReplyService;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
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

    @Autowired
    WxMemberService wxMemberService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService weixinService,
                                    WxSessionManager sessionManager) {

        if (!wxMessage.getMsgType().equals(XmlMsgType.EVENT)) {
            //TODO 可以选择将消息保存到本地
        }

        //当用户输入关键词如“你好”，“客服”等，并且有客服在线时，把消息转发给在线客服
        Map<String,Object> map = new HashMap<>(3);
        System.out.println("getToUser:"+wxMessage.getToUser());
        map.put("sourceId",wxMessage.getToUser());
        map.put("keyWord",wxMessage.getContent());
        try {
            List<WxReply> replyList = wxReplyService.getListByParams(map);
            if(replyList.size()!=0) {
                for (WxReply reply : replyList) {
                    if (reply.getKeyType().equals(WxConstants.REPLY_TYPE_TEXT)) {
                        return new TextBuilder().build(reply.getKeyValue(), wxMessage, weixinService);
                    } else if (reply.getKeyType().equals(WxConstants.MESSAGE_TYPE_NEWS)) {
                        JsonResult json = wxMemberService.getById(reply.getMaterialId());
                        if (json.isSuccess()) {
                            WxMaterial wxMaterial = (WxMaterial) json.getData();
                            boolean flag = new KefuNewsBuilder().pubMaterialToUserByKf(weixinService, wxMessage.getFromUser(), wxMaterial.getMediaId());
                        }
                        return null;
                    }
                }
            }else{
                //系统中找不到关键字恢复字段则调用机器人回复
               String answer = TulingApiUtil.getTulingResult(wxMessage.getContent());
                return new TextBuilder().build(answer, wxMessage, weixinService);
            }

        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }
        return null;
    }

}

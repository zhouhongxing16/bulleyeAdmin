package com.chris.bulleyeadmin.wechat.handler;

import com.alibaba.fastjson.JSON;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class UnsubscribeHandler extends AbstractHandler {

    @Autowired
    WxMemberService wxMemberService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) {
        WxMpUser userWxInfo = null;
        try {
            userWxInfo = wxMpService.getUserService().userInfo(wxMessage.getFromUser(), null);
            if (userWxInfo != null) {
                WxMember wxMember = JSON.parseObject(userWxInfo.toString(),WxMember.class);
                wxMember.setAccountId(wxMessage.getToUser());
                WxMember member = wxMemberService.getMemberByOpenId(wxMember.getOpenId());
                if(member==null){
                    wxMember.setSubscribe(false);
                    wxMember.setUnsubscribeTime(System.currentTimeMillis());
                    wxMemberService.add(wxMember);
                }else{
                    member.setSubscribe(false);
                    member.setUnsubscribeTime(System.currentTimeMillis());
                    wxMemberService.update(member);
                }
                this.logger.info("取消关注用户 OPENID: " + wxMember.getOpenId());
            }
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        // TODO 可以更新本地数据库为取消关注状态
        return null;
    }

}

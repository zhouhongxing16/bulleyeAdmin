package com.chris.bulleyeadmin.wechat.handler;

import java.util.Map;

import com.alibaba.fastjson2.JSON;
import com.chris.bulleyeadmin.wechat.builder.TextBuilder;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.result.WxMpUser;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class SubscribeHandler extends AbstractHandler {

    @Autowired
    WxMemberService wxMemberService;

    @Override
    public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                    Map<String, Object> context, WxMpService wxMpService,
                                    WxSessionManager sessionManager) throws WxErrorException {

        this.logger.info("新关注用户 OPENID: " + wxMessage.getFromUser());

        // 获取微信用户基本信息
        try {
            WxMpUser userWxInfo = wxMpService.getUserService()
                .userInfo(wxMessage.getFromUser(), null);
            WxMember wxMember = JSON.parseObject(userWxInfo.toString(),WxMember.class);
            if (userWxInfo != null) {
                wxMember.setSourceId(wxMessage.getToUser());
                subscribe(wxMember, wxMemberService);
            }
        } catch (WxErrorException e) {
            if (e.getError().getErrorCode() == 48001) {
                this.logger.info("该公众号没有获取用户信息权限！");
            }
        }


        WxMpXmlOutMessage responseResult = null;
        try {
            responseResult = this.handleSpecial(wxMessage);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        if (responseResult != null) {
            return responseResult;
        }

        try {
            return new TextBuilder().build("感谢关注", wxMessage, wxMpService);
        } catch (Exception e) {
            this.logger.error(e.getMessage(), e);
        }

        return null;
    }

    /**
     * 处理特殊请求，比如如果是扫码进来的，可以做相应处理
     */
    private WxMpXmlOutMessage handleSpecial(WxMpXmlMessage wxMessage)
        throws Exception {
        System.out.println(wxMessage.toString());
        //TODO
        return null;
    }

    //将用户关注事件的数据写入独立，防止多线程时的重复录入和菜单访问时获取用户信息的记录
    public static synchronized void  subscribe(WxMember wxMember,WxMemberService wxMemberService){
        WxMember member = wxMemberService.getMemberByOpenId(wxMember.getOpenId());
        if(member==null){
            wxMemberService.add(wxMember);
        }else{
            wxMember.setId(member.getId());
            wxMember.setSubscribe(true);
            wxMemberService.update(wxMember);
        }
    }
}

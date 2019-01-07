package com.chris.bulleyeadmin.wechat;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.sd4324530.fastweixin.message.BaseMsg;
import com.github.sd4324530.fastweixin.message.NewsMsg;
import com.github.sd4324530.fastweixin.message.req.BaseEvent;
import com.github.sd4324530.fastweixin.message.req.ReqType;
import com.github.sd4324530.fastweixin.message.req.TextReqMsg;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;

/**
 * 
 * @author lhs 2019-01-07
 *
 */
public class MyWeChatSupportServlet extends WeixinSupport{
	
    private static final Logger log  = LoggerFactory.getLogger(MyWeChatSupportServlet.class);

	@Override
	protected String getToken() {
		return "myToken";
	}
	
	@Override
	protected BaseMsg handleSubscribe(BaseEvent event) {
		log.debug("关注事件");
		String tousername=event.getToUserName();


        NewsMsg newsMsg = new NewsMsg();
        newsMsg.add("欢迎");
        newsMsg.setFromUserName(event.getToUserName());
        newsMsg.setToUserName(event.getFromUserName());
        newsMsg.setMsgType("text");
        return newsMsg;
//		return super.handleSubscribe(event);
	}
	
	@Override
	protected BaseMsg handleUnsubscribe(BaseEvent event) {
		log.debug("取消关注触发");
		String tousername=event.getToUserName();//发送给此微信号的信息

		return null;
	}
	
	@Override
	protected BaseMsg handleTextMsg(TextReqMsg msg) {
		log.debug("处理文本信息");
		String menu_state="";

		
		if(StringUtils.isNotBlank(menu_state)&&msg.getMsgType().equals(ReqType.TEXT)){
			TextReqMsg textReqMsg = (TextReqMsg) msg;
			log.debug("收到的文本消息："+textReqMsg.getContent());
    		log.debug("getToUserName："+textReqMsg.getToUserName());
		}
		return super.handleTextMsg(msg);
	}
	
}

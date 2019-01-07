package com.chris.bulleyeadmin.wechat;

import com.chris.bulleyeadmin.common.utils.Operalog;
import com.github.sd4324530.fastweixin.servlet.WeixinServletSupport;
import com.github.sd4324530.fastweixin.servlet.WeixinSupport;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author lhs 2019-01-07
 *
 */
@Controller
@RequestMapping("/weChat")
@Operalog("微信官方消息拦截器")
public class WeChatController extends WeixinServletSupport{

	@Override
	protected WeixinSupport getWeixinSupport() {
		return new MyWeixinSupportServlet();
	}

}

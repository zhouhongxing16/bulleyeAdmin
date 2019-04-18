package com.chris.bulleyeadmin.common.security;

import com.chris.bulleyeadmin.common.utils.HttpContextUtils;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.chris.bulleyeadmin.system.pojo.LoginRecord;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.LoginRecordService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Chris
 * @Date: 2019-04-18 14:41
 * @Description:登录成功回调类
 */
@Component
public class AuthenticationSuccessEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    @Autowired
    LoginRecordService loginRecordService;

    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent e) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) e.getSource();
        User user = (User) authenticationToken.getPrincipal();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);
        LoginRecord loginRecord = new LoginRecord();

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        loginRecord.setUsername(user.getUsername());
        loginRecord.setIp(ip);
        loginRecord.setOs(os);
        loginRecord.setBrowser(browser);
        loginRecord.setLoginLocation(IPUtils.getLocationByIP(ip));
        loginRecord.setStatus(1);
        loginRecord.setMessage("登录成功");
        loginRecordService.add(loginRecord);
    }
}
package com.chris.bulleyeadmin.common.security;

import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.HttpContextUtils;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.chris.bulleyeadmin.system.pojo.LoginRecord;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.LoginRecordService;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: Chris
 * @Date: 2019-04-18 14:31
 * @Description:登录失败回调类
 */
@Component
public class AuthenticationFailureListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {


    @Autowired
    LoginRecordService loginRecordService;

    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent  e) {
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) e.getSource();
        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        String ip = IPUtils.getIpAddr(request);
        LoginRecord loginRecord = new LoginRecord();

        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();
        loginRecord.setUsername(authenticationToken.getPrincipal().toString());
        loginRecord.setIp(ip);
        loginRecord.setOs(os);
        loginRecord.setBrowser(browser);
        loginRecord.setLoginLocation(IPUtils.getLocationByIP(ip));
        loginRecord.setStatus(0);
        loginRecord.setMessage("用户名或密码不正确...");
        loginRecordService.add(loginRecord);
    }
}
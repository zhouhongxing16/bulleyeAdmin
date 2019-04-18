package com.chris.bulleyeadmin.common.security;

import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.HttpContextUtils;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.chris.bulleyeadmin.system.pojo.LoginRecord;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.LoginRecordService;
import com.fasterxml.jackson.databind.ObjectMapper;
import eu.bitwalker.useragentutils.UserAgent;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 验证用户名密码正确后，生成一个token，并将token返回给客户端
 * 该类继承自UsernamePasswordAuthenticationFilter，重写了其中的2个方法
 * attemptAuthentication ：接收并解析用户凭证。
 * successfulAuthentication ：用户成功登录后，这个方法会被调用，我们在这个方法里生成token。
 *
 * @Auther: Chris
 * @Date: 2019-01-25 17:26
 * @Description:
 */
public class JWTLoginFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTLoginFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    // 接收并解析用户凭证
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(req.getInputStream(), User.class);
            UsernamePasswordAuthenticationToken authenticationToken =  new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), new ArrayList<>());
            return authenticationManager.authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    // 用户成功登录后，这个方法会被调用，我们在这个方法里生成token
    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication auth) throws IOException, ServletException {
        User user = (User) auth.getPrincipal();

        String token = Jwts.builder()
                .setSubject(user.toString())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 24 * 1000))
                .signWith(SignatureAlgorithm.HS512, "BulleyeAdminSecret")
                .compact();
        res.addHeader("Authorization", "Bearer " + token);
        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        String data = new JsonResult(true, map, "登录成功！", null, HttpStatus.OK.value()).toString();
        req.setCharacterEncoding("UTF-8");
        res.setCharacterEncoding("UTF-8");
        PrintWriter writer = res.getWriter();
        writer.write(data);
        writer.flush();
        writer.close();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        Map<String, Object> map = new HashMap<>();
        String data = new JsonResult(false, map, "登录失败！用户名或密码错误！", null, HttpStatus.UNAUTHORIZED.value()).toString();
        response.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write(data);
        writer.flush();
        writer.close();
    }
}

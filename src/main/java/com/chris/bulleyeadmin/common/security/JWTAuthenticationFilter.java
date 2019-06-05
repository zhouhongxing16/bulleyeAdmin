package com.chris.bulleyeadmin.common.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.User;
import io.jsonwebtoken.Jwts;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Auther: Chris
 * @Date: 2019-01-25 17:28
 * @Description: token的校验
 * 该类继承自BasicAuthenticationFilter，在doFilterInternal方法中，
 * 从http头的Authorization 项读取token数据，然后用Jwts包提供的方法校验token的合法性。
 * 如果校验通过，就认为这是一个取得授权的合法请求
 */
public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        String header = request.getHeader("Authorization");
        if (header == null || !header.startsWith("Bearer ")) {
            chain.doFilter(request, response);
            return;
        }
        UsernamePasswordAuthenticationToken authentication = getAuthentication(request, response);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        chain.doFilter(request, response);

    }

    private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String token = request.getHeader("Authorization");
        if (!"null".equals(token.replace("Bearer ", "").trim())) {
            String jsonObject = null;
            User user = null;
            try {
                jsonObject = Jwts.parser().setSigningKey("BulleyeAdminSecret")
                        .parseClaimsJws(token.replace("Bearer ", "")).getBody().getSubject();
                JSONObject obj = JSON.parseObject(jsonObject);
                JSONArray ja = JSONArray.parseArray(obj.getString("authorities"));
                List<Role> roleList = JSONArray.parseArray(obj.getString("role"), Role.class);
                user = new User(obj.getString("username"), "", ja.toJavaList(GrantedAuthority.class));
                user.setStaffId(obj.getString("staffId"));
                user.setId(obj.getString("id"));
                user.setOrganizationId(obj.getString("organizationId"));
                user.setDepartmentId(obj.getString("departmentId"));
                user.setRole(roleList);
            } catch (Exception e) {
                return getFailAuthenticationTokenResult(request, response);
            }
            if (user != null) {
                return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
            } else {
                return new UsernamePasswordAuthenticationToken(null, null, null);
            }
        } else {
            return getFailAuthenticationTokenResult(request, response);
        }
    }

    private UsernamePasswordAuthenticationToken getFailAuthenticationTokenResult(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        System.out.println("授权认证失败，请重新登录");
        String msg = new JsonResult(false, null, "授权认证失败，请重新登录！", null, HttpStatus.UNAUTHORIZED.value()).toString();
        writer.write(msg);
        writer.flush();
        writer.close();
        return null;
    }
}

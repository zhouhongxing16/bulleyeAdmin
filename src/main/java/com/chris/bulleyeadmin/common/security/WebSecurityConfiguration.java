package com.chris.bulleyeadmin.common.security;

import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-22 9:23
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {


    @Bean
    UserDetailsService customUserService(){
        return new MyUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserService());
        //auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
            // 如果基于token，不需要session的情况取消下面这段注释
            //http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();
            http.authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/assets/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/imagevcode").permitAll()
                .anyRequest().authenticated()


                    //登录相关
                .and()
                .formLogin()
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                        /*httpServletResponse.setContentType("application/json;charset=utf-8");
                        httpServletResponse.setStatus(200);
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"success\":true,\"message\":\"登录成功\"}");
                        out.flush();
                        out.close();*/
                        JSONObject res = new JSONObject();
                        res.put("success",true);
                        res.put("msg","登录成功");
                        response.setStatus(200);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().append(res.toString());
                    }
                })
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
                        /*httpServletResponse.setContentType("application/json;charset=utf-8");
                        PrintWriter out = httpServletResponse.getWriter();
                        out.write("{\"success\":false,\"message\":\"用户名或密码错误\"}");
                        out.flush();
                        out.close();*/
                        JSONObject res = new JSONObject();
                        res.put("success",false);
                        res.put("msg","登录失败,请检查账号密码是否正确");
                        response.setStatus(500);
                        response.setContentType("application/json;charset=UTF-8");
                        response.getWriter().append(res.toString());
                    }
                })
                .loginProcessingUrl( "/formLogin" )
                .loginPage("/login").permitAll()
                .usernameParameter("username")
                .passwordParameter("password")
                .defaultSuccessUrl("/home");
        http.headers().frameOptions().sameOrigin();//允许页面中嵌套iframe
        http.logout().logoutUrl( "/logout" )
                .invalidateHttpSession(true)
                .deleteCookies( "JSESSIONID" )
                .logoutSuccessUrl( "/login" ).permitAll();

    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring().antMatchers("/css/**");
    }



}

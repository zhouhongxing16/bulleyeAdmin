package com.chris.bulleyeadmin.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-22 9:23
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    MyUserDetailsService myUserDetailsService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //将验证过程交给自定义验证工具
        auth.userDetailsService(myUserDetailsService);
        //auth.userDetailsService(customUserService()).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin();//允许页面中嵌套iframe
        http.csrf().disable()
                // 开启跨域
                .cors().and().authorizeRequests()
                .antMatchers("/login").permitAll()
                .antMatchers("/unauth").permitAll()
                .antMatchers("/imagevcode").permitAll()
                .antMatchers("/wx/portal/**").permitAll()
                .antMatchers("/file/view/**").permitAll()
                .antMatchers("/file/download/**").permitAll()
                .antMatchers("/api/file/**").permitAll()
//                .antMatchers("/file/uploadfile").permitAll()
                .antMatchers("/message/sendVerificationCode").permitAll()
                .antMatchers("/message/verificationCode").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTLoginFilter(authenticationManager()))
                .addFilter(new JWTAuthenticationFilter(authenticationManager()))
                .formLogin().loginProcessingUrl("/formLogin")
                .loginPage("/unauth").permitAll()
                .usernameParameter("username")
                .passwordParameter("password").defaultSuccessUrl("/home");

        http.logout().logoutUrl("/logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/login").permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //解决静态资源被拦截的问题
        web.ignoring()
                .antMatchers("/assets/**")
                .antMatchers("/favicon.ico")
                .antMatchers("/index.html")
                .antMatchers("/**.js")
                .antMatchers("/**.css")
                .antMatchers("/**.txt");
        web.ignoring().antMatchers("/v2/api-docs", "/swagger-resources/**", "/swagger-ui.html**", "/webjars/**", "/medical-provider/api-docs");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); // 1 设置访问源地址
        corsConfiguration.addAllowedHeader("*"); // 2 设置访问源请求头
        corsConfiguration.addAllowedMethod("*"); // 3 设置访问源请求方法
        corsConfiguration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }


}

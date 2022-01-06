package com.chris.bulleyeadmin.common.config;

import com.chris.bulleyeadmin.common.security.AccessLimitInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${uploadConfig.uploadPath}")
    String uploadPath;

    @Value("${uploadConfig.prefix}")
    String prefix;


    @Resource
    private AccessLimitInterceptor accessLimitInterceptor;

    /**
     * 拦截器配置
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 增加一个拦截器，检查会话，URL都使用此拦截器
        registry.addInterceptor(accessLimitInterceptor)
                .addPathPatterns("/**")
                // 不被拦截的路径
                .excludePathPatterns("一般是登录注册这种不拦截的路径");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地静态资源映射
        registry.addResourceHandler(prefix+"/**").addResourceLocations("file:"+uploadPath+"/");

    }

}

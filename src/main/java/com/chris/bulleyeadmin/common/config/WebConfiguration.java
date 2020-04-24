package com.chris.bulleyeadmin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Value("${uploadConfig.uploadPath}")
    String uploadPath;

    @Value("${uploadConfig.prefix}")
    String prefix;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地静态资源映射
        registry.addResourceHandler(prefix+"/**").addResourceLocations("file:"+uploadPath+"/");

    }

}

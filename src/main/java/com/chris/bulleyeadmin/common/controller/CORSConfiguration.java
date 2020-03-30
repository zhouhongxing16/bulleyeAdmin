package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.entity.FileUploadPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CORSConfiguration extends WebMvcConfigurationSupport {

    @Autowired
    FileUploadPath fileUploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //本地静态资源映射
        String path = fileUploadPath.getUploadPath();
        registry.addResourceHandler("/api/file/**").addResourceLocations("file:"+path);

    }

}

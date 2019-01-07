package com.chris.bulleyeadmin.common.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ServerConfig
{
    @Value("${serverconfig.uploadPath}")
    private String uploadPath;

    public String getUploadPath()
    {
        return this.uploadPath;
    }
}

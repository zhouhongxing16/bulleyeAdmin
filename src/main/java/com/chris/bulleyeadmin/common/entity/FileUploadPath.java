package com.chris.bulleyeadmin.common.entity;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FileUploadPath {

    @Value("${serverConfig.uploadPath}")
    private String uploadPath;
    @Value("${serverConfig.isDebug}")
    private Boolean isDebug;

    @Value("${serverConfig.uploadUrl}")
    private String uploadUrl;

    public String getUploadPath() {
        return uploadPath;
    }

    public void setUploadPath(String uploadPath) {
        this.uploadPath = uploadPath;
    }


    public String getUploadUrl() {
        return uploadUrl;
    }

    public void setUploadUrl(String uploadUrl) {
        this.uploadUrl = uploadUrl;
    }

    public Boolean getDebug() {
        return isDebug;
    }

    public void setDebug(Boolean debug) {
        isDebug = debug;
    }
}

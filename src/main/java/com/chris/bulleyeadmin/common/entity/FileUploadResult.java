package com.chris.bulleyeadmin.common.entity;

import org.springframework.stereotype.Component;

import java.io.Serializable;


@Component
public class FileUploadResult implements Serializable {

    private int status;

    private int error;

    private String id;

    private Boolean success;

    private String message;

    private String url;

    private String downloadPath;

    private String fileName;

    private String uploaded;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDownloadPath() {
        return downloadPath;
    }

    public void setDownloadPath(String downloadPath) {
        this.downloadPath = downloadPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUploaded() {
        return uploaded;
    }

    public void setUploaded(String uploaded) {
        this.uploaded = uploaded;
    }
}
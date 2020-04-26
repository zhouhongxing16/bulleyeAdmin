package com.chris.bulleyeadmin.common.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class AliOSSConfig implements Serializable {

    public AliOSSConfig() {
    }


    @Value("${aliOSSConfig.endpoint}")
    private String endpoint;

    @Value("${aliOSSConfig.accessKeyId}")
    private String accessKeyId;

    @Value("${aliOSSConfig.accessKeySecret}")
    private String accessKeySecret;

    @Value("${aliOSSConfig.bucketName}")
    private String bucketName;

    @Value("${aliOSSConfig.domainName}")
    private String domainName;

    private String firstKey;


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKeyId() {
        return accessKeyId;
    }

    public void setAccessKeyId(String accessKeyId) {
        this.accessKeyId = accessKeyId;
    }

    public String getAccessKeySecret() {
        return accessKeySecret;
    }

    public void setAccessKeySecret(String accessKeySecret) {
        this.accessKeySecret = accessKeySecret;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getFirstKey() {
        return firstKey;
    }

    public void setFirstKey(String firstKey) {
        this.firstKey = firstKey;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }
}

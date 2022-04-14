package com.chris.bulleyeadmin.common.config;

import io.minio.MinioClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioConfig {

    /**
     * endPoint是一个URL，域名，IPv4或者IPv6地址
     */
    private String endpoint;

    /**
     * 端口
     */
    private int port;

    /**
     * accessKey类似于用户ID，用于唯一标识你的账户
     */
    private String accessKey;

    /**
     * secretKey是你账户的密码
     */
    private String secretKey;

    /**
     * 如果是true，则用的是https而不是http,默认值是true
     */
    private Boolean secure;

    /**
     * 默认存储桶
     */
    private String bucketName;

    /**
     * 配置目录
     */
    private String configDir;
/*
    @Bean
    public MinioClient getMinClient() {
        return MinioClient.builder()
                .endpoint(endpoint, port, secure)//http
                .credentials(accessKey, secretKey)
                .build();
    }*/


    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Boolean getSecure() {
        return secure;
    }

    public void setSecure(Boolean secure) {
        this.secure = secure;
    }

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getConfigDir() {
        return configDir;
    }

    public void setConfigDir(String configDir) {
        this.configDir = configDir;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
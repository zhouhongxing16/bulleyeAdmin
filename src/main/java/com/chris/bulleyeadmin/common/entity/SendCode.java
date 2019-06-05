package com.chris.bulleyeadmin.common.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Component
public class SendCode implements Serializable {

    @Value("${aliyunSMS.accessKeyId}")
    private   String accessKeyId;
    @Value("${aliyunSMS.accessKeySecret}")
    private  String accessKeySecret;
    @Value("${aliyunSMS.signName}")
    private  String signName;
    @Value("${aliyunSMS.product}")
    private  String product;
    @Value("${aliyunSMS.domain}")
    private  String domain;


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

    public String getSignName() {
        return signName;
    }

    public void setSignName(String signName) {
        this.signName = signName;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}

package com.chris.bulleyeadmin.common.entity;

import org.springframework.util.StringUtils;

public enum  SendCodeEnum  {

    //验证码通知短信模版code
    SMS_122282577("VERIFICATION_CODE"),

    //注册验证码通知短信模版code
    SMS_85725014("REGISTER_VERIFICATION_CODE");

    private String typeCode;

    public Object getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }



     SendCodeEnum(String typeCode) {
        this.typeCode = typeCode;
    }

    public static SendCodeEnum getEnumValue(String typeCode) {
        if (!StringUtils.isEmpty(typeCode)) {
            for (SendCodeEnum sendCodeEnum : SendCodeEnum.values()) {
                if (sendCodeEnum.getTypeCode().equals(typeCode)) {
                    return sendCodeEnum;
                }
            }
        }
        return null;
    }

}

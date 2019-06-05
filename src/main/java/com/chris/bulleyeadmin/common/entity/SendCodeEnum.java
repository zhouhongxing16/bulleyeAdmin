package com.chris.bulleyeadmin.common.entity;

import org.springframework.util.StringUtils;

public enum  SendCodeEnum  {
    //科室报到通知短信模版code
    SMS_133972087("SECTION_CHECK_IN_CODE"),

    //验证码通知短信模版code
    SMS_122282577("VERIFICATION_CODE"),

    //注册验证码通知短信模版code
    SMS_85725014("REGISTER_VERIFICATION_CODE"),

    //选课通知短信模版code
    SMS_137550352("STUDENT_SELECTIVE_COURSE"),

    //面试通知短信模版code
    //{name}，您好： 请于${startDate}到${hospitalName}的${address}，参加我院组织的《住院医师规范化培训》的面试。特此通知
    SMS_139971314("TRAIN_INTERVIEW_CODE");

    private String typeCode;

    public String getTypeCode() {
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

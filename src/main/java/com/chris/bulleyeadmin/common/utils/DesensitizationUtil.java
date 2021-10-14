package com.chris.bulleyeadmin.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * @author liulei
 * @date 2020/3/25 15:23
 */
public class DesensitizationUtil {
    /**
     * 手机号码前三后四脱敏
     */
    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

    /**
     * 身份证前六后四脱敏  即出生日期 用*
     */
    public static String idEncrypt(String id) {
        if (StringUtils.isEmpty(id) || (id.length() < 8)) {
            return id;
        }
        return id.replaceAll("(?<=\\w{4})\\w(?=\\w{4})", "*");
    }
}

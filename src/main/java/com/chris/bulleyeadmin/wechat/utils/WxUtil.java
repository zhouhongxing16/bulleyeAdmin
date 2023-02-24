package com.chris.bulleyeadmin.wechat.utils;


import com.alibaba.fastjson2.JSONObject;

public class WxUtil {

    public static int resultToGetCode(String result) {
        if (result == null || "null".equals(result) || "".equals(result)) {
            return 0;
        }
        JSONObject jsonObject = JSONObject.parseObject(result);
        return jsonObject.getInteger("errcode");
    }
}

package com.chris.bulleyeadmin.wechat;

/**
 * @ClassName CheckUtil
 * @Description 微信校验工具类
 * @Author zhengyou
 * @Date 2018/10/26 15:08
 * @Version 1.0
 **/

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class CheckUtil {


    /**
     * 校验
     * @param signature
     * @param timestamp
     * @param nonce
     * @return
     */
    public static boolean checkSignature(String token, String signature, String timestamp, String nonce) {
        String[] arr = new String[]{token, timestamp, nonce};
        Arrays.sort(arr);
        //生成字符串
        StringBuffer content = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            content.append(arr[i]);
        }
        //sha1加密
        String temp = sha1(content.toString()).toLowerCase();
        return temp.equals(signature);
    }

    /**
     * sha1加密
     *
     * @author qincd
     * @date Nov 3, 2014 4:16:39 PM
     */
    public static String sha1(String str) {
        if (str == null || str.length() == 0) {
            return "";
        }
        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] bytes = md.digest(str.getBytes());
            return byte2Hex(bytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new RuntimeException("SHA1加密出错");
        }
    }

    public static String byte2Hex(byte[] data) {
        if (data == null || data.length == 0) {
            return "";
        }
        StringBuilder sbu = new StringBuilder();
        for (int i = 0; i < data.length; i++) {
            if ((data[i] & 0xff) < 0x10) {
                sbu.append("0");
            }
            sbu.append(Integer.toHexString(data[i] & 0xff));
        }
        return sbu.toString();
    }

}


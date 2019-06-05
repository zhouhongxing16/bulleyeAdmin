package com.chris.bulleyeadmin.common.utils;


import com.github.pagehelper.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;

public class Help {

    private static final Logger LOGGER = LoggerFactory.getLogger(DateUtils.class);
    public static final AntPathMatcher antPathMatcher = new AntPathMatcher();

    //获取请求数据
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }
        body = stringBuilder.toString();
        return body;
    }

    public static long getCurrentTimeMillis() {
        return System.currentTimeMillis();
    }


    public static void startPage(Map<String, Object> params) {
        if(params.get("pageNum") != null){
            if(params.get("pageSize").toString().equals( "0" )){
                params.remove( "pageNum" );
                params.remove( "pageSize" );
            } else {
                PageHelper.startPage(params);
            }
        }
    }
}

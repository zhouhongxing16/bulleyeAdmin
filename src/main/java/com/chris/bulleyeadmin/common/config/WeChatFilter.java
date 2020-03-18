package com.chris.bulleyeadmin.common.config;

import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class WeChatFilter {


    private static WeChatFilter instance = null;

    Properties prop = null;

    List<String> filterList = null;

    private WeChatFilter() {
    }


    public static WeChatFilter getInstance() {
        if (null == instance) {
            instance = new WeChatFilter();
            instance.init();
        }
        return instance;
    }

    private synchronized void init() {
        filterList = new ArrayList<>();
        filterList.add("properties/wechat-filter.properties");

        System.out.println(filterList);
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("properties/wechat-filter.properties");
        prop = new Properties();
        try {
            prop.load(is);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(1);
    }

    public boolean getUrlPassFlag(String url) {

        return  prop.containsKey(url)?true:false;
        /*Boolean flag = false;
        for(String str : filterList){
            if(str.equals(url)){
                flag = true;
            }
        }
        return flag;*/
    }
    public boolean getActionKey(String url) {
        Boolean flag = false;
        for(String str : filterList){
            if(str.equals(url)){
                flag = true;
            }
        }
        return flag;
    }

}

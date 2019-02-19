package com.chris.bulleyeadmin.wechat.utils;


import com.chris.bulleyeadmin.wechat.config.WxMpConfiguration;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;

public class WxMenuUtil {

    public static String WxMenuBuilder(String appId){
        WxMpService wxService = WxMpConfiguration.getMpServices().get(appId);


        try {
            WxMenu menu = new WxMenu();

            wxService.getMenuService().menuCreate("123");
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return "";
    }
}

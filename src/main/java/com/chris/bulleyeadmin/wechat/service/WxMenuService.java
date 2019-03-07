package com.chris.bulleyeadmin.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.config.WxMpConfiguration;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMenuMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class WxMenuService extends BaseService<WxMenu> {

    @Autowired
    WxMenuMapper wxMenuMapper;

    @Autowired
    WxAccountMapper wxAccountMapper;


    @Override
    public BaseMapper<WxMenu> getMapper() {
        return wxMenuMapper;
    }

    public List<WxMenu> selectlist(WxMenu wxMenu) {
        return wxMenuMapper.selectlist(wxMenu);
    }

    public List<WxMenu> selectTwolist(WxMenu wxMenu) {
        return wxMenuMapper.selectTwolist(wxMenu);
    }

    public String createWxMenu(String appId){
        WxMpService wxService = WxMpConfiguration.getMpServices().get(appId);

        WxAccount account = new WxAccount();
        account.setAppId(appId);
        WxAccount wxAccount = wxAccountMapper.selectOne(account);

        WxMenu menu = new WxMenu();
        menu.setAppId(appId);
        menu.setParentId("0");
        List<WxMenu> firstMenuList = wxMenuMapper.selectlist(menu);

        WxMenu menu1 = new WxMenu();
        menu1.setAppId(appId);

        //要传向接口的类
        me.chanjar.weixin.common.bean.menu.WxMenu createMenu = new me.chanjar.weixin.common.bean.menu.WxMenu();
        List<WxMenuButton> wxMenuButtonList = new ArrayList<>();
        firstMenuList.stream().forEach(item ->{
            JSONObject menuJson = new JSONObject();
            //若需要获取用户信息，则进行授权  url,类型,state
            if ("1".equals(item.getAuthor())&&"view".equals(item.getType())){
                item.setUrl(wxService.oauth2buildAuthorizationUrl(item.getUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, wxAccount.getStatus()));
            }

            //一级菜单
            WxMenuButton wxMenuButtonOne = new WxMenuButton();
            wxMenuButtonOne.setAppId(item.getAppId());
            wxMenuButtonOne.setKey(item.getKey());
            wxMenuButtonOne.setMediaId(item.getMediaId());
            wxMenuButtonOne.setName(item.getName());
            wxMenuButtonOne.setPagePath(item.getPagePath());
            wxMenuButtonOne.setType(item.getType());
            wxMenuButtonOne.setUrl(item.getUrl());

            //二级菜单
            List<WxMenuButton> subWxMenuButton = new ArrayList<>();
            menu1.setParentId(item.getId());
            List<WxMenu> secondMenuList = wxMenuMapper.selectlist(menu1);

            secondMenuList.stream().forEach(item2 ->{
                if ("1".equals(item2.getAuthor())&&"view".equals(item2.getType())){
                    item2.setUrl(wxService.oauth2buildAuthorizationUrl(item2.getUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, wxAccount.getStatus()));
                }
                WxMenuButton wxMenuButtonSecond = new WxMenuButton();
                wxMenuButtonSecond.setAppId(item.getAppId());
                wxMenuButtonSecond.setKey(item.getKey());
                wxMenuButtonSecond.setMediaId(item.getMediaId());
                wxMenuButtonSecond.setName(item.getName());
                wxMenuButtonSecond.setPagePath(item.getPagePath());
                wxMenuButtonSecond.setType(item.getType());
                wxMenuButtonSecond.setUrl(item.getUrl());

                subWxMenuButton.add(wxMenuButtonSecond);
            });

            wxMenuButtonOne.setSubButtons(subWxMenuButton);

            wxMenuButtonList.add(wxMenuButtonOne);
        });

        createMenu.setButtons(wxMenuButtonList);

        try {
            wxService.getMenuService().menuCreate(createMenu);
        } catch (WxErrorException e) {
            e.printStackTrace();
            return e.getStackTrace().toString();
        }

        return "";
    }


}

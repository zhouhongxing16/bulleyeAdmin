package com.chris.bulleyeadmin.wechat.service;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.common.basemapper.BaseMapper;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.config.WxMpConfiguration;
import com.chris.bulleyeadmin.wechat.mapper.WxAccountMapper;
import com.chris.bulleyeadmin.wechat.mapper.WxMenuMapper;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import com.chris.bulleyeadmin.wechat.utils.WxUtil;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class WxMenuService extends BaseService<WxMenu> {

    @Autowired
    WxMenuMapper wxMenuMapper;

    @Autowired
    WxAccountMapper wxAccountMapper;

    @Autowired
    WxMpConfiguration wxMpConfiguration;

    @Override
    public BaseMapper<WxMenu> getMapper() {
        return wxMenuMapper;
    }

    public List<WxMenu> selectlist(WxMenu wxMenu) {
        return wxMenuMapper.selectlist(wxMenu);
    }

    public JsonResult createWxMenu(String accountId){
        WxAccount account = new WxAccount();
        account.setId(accountId);
        WxAccount wxAccount = wxAccountMapper.selectOne(account);

        WxMpService wxService = WxMpConfiguration.getMpServices().get(wxAccount.getAppId());

        WxMenu menu = new WxMenu();
        menu.setAccountId(wxAccount.getId());
        menu.setParentId("0");
        List<WxMenu> firstMenuList = wxMenuMapper.selectlist(menu);

        WxMenu menu1 = new WxMenu();
        menu1.setAccountId(wxAccount.getId());

        //要传向接口的类
        me.chanjar.weixin.common.bean.menu.WxMenu createMenu = new me.chanjar.weixin.common.bean.menu.WxMenu();
        List<WxMenuButton> wxMenuButtonList = new ArrayList<>();
        firstMenuList.stream().forEach(item ->{
            //若需要获取用户信息，则进行授权  url,类型,state
            if ("1".equals(item.getAuthor())&&"view".equals(item.getType())){
                item.setUrl(wxService.oauth2buildAuthorizationUrl(item.getUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, wxAccount.getStatus()));
            }

            //一级菜单
            WxMenuButton wxMenuButtonOne = new WxMenuButton();
            wxMenuButtonOne.setAppId(item.getAppId());
            wxMenuButtonOne.setKey(item.getKeyValue());
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
                wxMenuButtonSecond.setKey(item.getKeyValue());
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
            System.out.println("结构"+wxService.getMenuService().menuCreate(createMenu));
            int count = WxUtil.resultToGetCode(wxService.getMenuService().menuCreate(createMenu));
            System.out.println("菜单创建结果"+count);
            String msg = count==0?"创建成功":"创建失败！";
            return new JsonResult(count==0?true:false,null,msg,count, HttpStatus.OK.value());

        } catch (WxErrorException e) {
            e.printStackTrace();
            return new JsonResult(false,null,"创建失败！",null, HttpStatus.OK.value());
        }

    }

}

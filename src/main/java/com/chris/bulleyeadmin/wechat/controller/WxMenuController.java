package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.pojo.WxMenu;
import com.chris.bulleyeadmin.wechat.service.WxMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/wxmenu")
public class WxMenuController extends BaseController<WxMenu> {

    @Autowired
    WxMenuService wxMenuService;

    @Override
    public BaseService<WxMenu> getService() {
        return wxMenuService;
    }

    @Override
    public String getViewPrefix() {
        return "wxmenu";
    }
}

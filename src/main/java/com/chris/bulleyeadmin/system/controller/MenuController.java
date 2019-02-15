package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.MenuService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:48
 */
@Controller
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

    @Autowired
    MenuService menuService;

    @Override
    public BaseService<Menu> getService() {
        return menuService;
    }

    @Override
    public String getViewPrefix() {
        return "menu";
    }

    @ResponseBody
    @RequestMapping("/getAllMenus")
    public JsonResult getAllMenus() {
        User user = AuthUtil.getCurrentUser();
        System.out.println(user.getUsername());
        List<MenuDto> menuList = menuService.getAllMenus();
        return new JsonResult(true, menuList);
    }

    @ResponseBody
    @RequestMapping("/getMenusByAccountId")
    public JsonResult getMenusByAccountId() {
        User user = AuthUtil.getCurrentUser();
        List<MenuDto> menuList = menuService.getMenusByAccountId(user.getId());
        return new JsonResult(true, menuList);
    }

    @ResponseBody
    @RequestMapping("/getMenusByRoleId")
    public JsonResult getMenusByRoleId(String roleId) {
        List<Menu> menuList = menuService.getMenusByRoleId(roleId);
        return new JsonResult(true, menuList);
    }
}

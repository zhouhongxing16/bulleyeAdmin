package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.dto.MenuDto;
import com.chris.bulleyeadmin.pojo.JsonResult;
import com.chris.bulleyeadmin.pojo.Menu;
import com.chris.bulleyeadmin.service.BaseService;
import com.chris.bulleyeadmin.service.MenuService;
import com.chris.bulleyeadmin.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public JsonResult getAllMenus(){
        List<MenuDto> menuList = menuService.getAllMenus();
        return new JsonResult(true,menuList);
    }

    @ResponseBody
    @RequestMapping("/getMenusByAccountId")
    public JsonResult getMenusByAccountId(){
        List<MenuDto> menuList = menuService.getMenusByAccountId(AuthUtil.getCurrentUser().getId());
        return new JsonResult(true,menuList);
    }

    @ResponseBody
    @RequestMapping("/getMenusByRoleId")
    public JsonResult getMenusByRoleId(String roleId){
        List<Menu> menuList = menuService.getMenusByRoleId(roleId);
        return new JsonResult(true,menuList);
    }
}

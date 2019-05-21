package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.utils.Help;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.MenuService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:48
 */
@OperationLog("菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

    @Autowired
    MenuService menuService;

    @Override
    public BaseService<Menu> getService() {
        return menuService;
    }

    @OperationLog("获取所有菜单")
    @ResponseBody
    @RequestMapping("/getAllMenus")
    public JsonResult getAllMenus() {
        List<MenuDto> menuList = menuService.getAllMenus();
        return new JsonResult(true,menuList,null,null, HttpStatus.OK.value());
    }

    @OperationLog("获取组织菜单")
    @ResponseBody
    @RequestMapping("/getOrganizationMenus")
    public JsonResult getOrganizationMenus(@RequestBody Map<String,Object> map) {
        if(map.get("organizationId")==null){
            User user = AuthUtil.getCurrentUser();
            map.put("organizationId",user.getOrganizationId());
        }
        List<MenuDto> menuList = menuService.getOrganizationMenus(map);
        return new JsonResult(true, menuList,null,null, HttpStatus.OK.value());
    }


    @OperationLog("获取组织授权后菜单")
    @ResponseBody
    @RequestMapping("/getOrganizationAuthMenus")
    public JsonResult getOrganizationAuthMenus(@RequestBody Map<String,Object> map) {
        User user = AuthUtil.getCurrentUser();
        map.put("organizationId",user.getOrganizationId());
        List<MenuDto> menuList = menuService.getOrganizationAuthMenus(map);
        return new JsonResult(true, menuList,null,null, HttpStatus.OK.value());
    }

    @OperationLog("获取登录用户菜单")
    @ResponseBody
    @RequestMapping("/getMenusByAccountId")
    public JsonResult getMenusByAccountId() {
        User user = AuthUtil.getCurrentUser();
        List<MenuDto> menuList = menuService.getMenusByAccountId(user.getId());
        return new JsonResult(true, menuList,null,null, HttpStatus.OK.value());
    }

    @OperationLog("根据角色或缺菜单")
    @ResponseBody
    @RequestMapping("/getMenusByRoleId")
    public JsonResult getMenusByRoleId(String roleId) {
        List<Menu> menuList = menuService.getMenusByRoleId(roleId);
        return new JsonResult(true, menuList,null,null, HttpStatus.OK.value());
    }

}

package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.RoleMenu;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;
import com.chris.bulleyeadmin.system.service.RoleMenuAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date: 2019-05-16 21:29
 */
@Api(tags = "rolemenuauth", description = "角色菜单权限")
@OperationLog("角色菜单权限")
@RestController
@RequestMapping("/rolemenuauth")
public class RoleMenuAuthController extends BaseController<RoleMenuAuth> {

    @Autowired
    RoleMenuAuthService roleMenuAuthService;

    @Override
    public BaseService getService() {
        return roleMenuAuthService;
    }

    @OperationLog("根据菜单和角色获取功能授权")
    @RequestMapping("/getAuthByMenuId/{menuId}")
    public Object getAuthByMenuId(@PathVariable("menuId") String menuId) {
        Object obj = roleMenuAuthService.getAuthByMenuAndRoleId(menuId);
        return obj;
    }

    @OperationLog("根据菜单和角色获取功能授权")
    @RequestMapping("/getMenuAndAuthByRoleId/{roleId}")
    public Object getMenuAndAuthByRoleId(@PathVariable("roleId") String roleId) {
        Object obj = roleMenuAuthService.getMenuAndAuthByRoleId(roleId);
        return obj;
    }

    @OperationLog("角色菜单授权")
    @ApiOperation(value = "角色菜单授权")
    @PostMapping("/createRoleMenuAuth")
    public JsonResult createRoleMenuAuth(@RequestBody List<RoleMenuAuth> list){
        return roleMenuAuthService.createRoleMenuAuth(list);
    }

}
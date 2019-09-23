package com.chris.bulleyeadmin.system.controller;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;

import com.chris.bulleyeadmin.system.service.RoleMenuAuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-05-16 21:29
 */
@Api(tags = "rolemenuauth", description = "角色菜单权限")
@OperationLog("角色菜单权限")
@RestController
@RequestMapping("/rolemenuauth")
public class RoleMenuAuthController extends BaseController<RoleMenuAuth>{

     @Autowired
    RoleMenuAuthService roleMenuAuthService;

    @Override
    public BaseService getService() {
        return roleMenuAuthService;
    }

    @OperationLog("获取当前用户角色授权")
    @RequestMapping("/getCurrentUserRoleAuth")
    public Object getCurrentUserRoleAuth(@RequestBody Map<String,String> param){
        Object obj = roleMenuAuthService.getAuthByRoleId();
        return obj;
    }
    
}
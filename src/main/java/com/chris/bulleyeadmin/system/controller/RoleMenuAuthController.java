package com.chris.bulleyeadmin.system.controller;
import com.chris.bulleyeadmin.system.pojo.RoleMenuAuth;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;

import com.chris.bulleyeadmin.system.service.RoleMenuAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-05-16 21:29
 */
@RestController
@RequestMapping("/rolemenuauth")
public class RoleMenuAuthController extends BaseController{

     @Autowired
    RoleMenuAuthService roleMenuAuthService;

    @Override
    public BaseService getService() {
        return roleMenuAuthService;
    }
    
}
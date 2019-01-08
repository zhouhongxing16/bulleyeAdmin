package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:50
 */
@Controller
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

    @Autowired
    RoleService roleService;

    @Override
    public BaseService<Role> getService() {
        return roleService;
    }

    @Override
    public String getViewPrefix() {
        return "role";
    }

    @ResponseBody
    @RequestMapping("/getAllRoles")
    public JsonResult getAllRoles(){
        Map<String,Object> map = new HashMap<>(2);
        List<Role> roles = roleService.getMapper().selectAll();
        return new JsonResult(true,roles);
    }
}

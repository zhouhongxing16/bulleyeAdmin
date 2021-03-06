package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.RoleMenu;
import com.chris.bulleyeadmin.system.service.RoleMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(tags = "角色菜单", produces = "角色菜单")
@OperationLog("角色菜单")
@RestController
@RequestMapping("/rolemenu")
public class RoleMenuController extends BaseController<RoleMenu> {

    @Autowired
    RoleMenuService roleMenuService;

    @Override
    public BaseService<RoleMenu> getService() {
        return roleMenuService;
    }

    @OperationLog("角色菜单授权")
    @ApiOperation(value = "角色菜单授权")
    @PostMapping("/createRoleMenu")
    public JsonResult createRoleMenu(@RequestBody List<RoleMenu> list){
        return roleMenuService.createRoleMenu(list);
    }
}

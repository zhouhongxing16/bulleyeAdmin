package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import com.chris.bulleyeadmin.system.service.AccountRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "帐号角色管理", produces = "帐号角色管理")
@RestController
@RequestMapping("/accountrole")
public class AccountRoleController extends BaseController<AccountRole> {

    @Autowired
    AccountRoleService accountRoleService;

    @Override
    public BaseService<AccountRole> getService() {
        return accountRoleService;
    }

    @OperationLog("保存账号角色")
    @RequestMapping("/saveAccountRoles")
    @ApiOperation(value = "保存账号角色", notes = "")
    public JsonResult saveAccountRoles(@RequestBody Map<String,String> map){
       return accountRoleService.saveAccountRoles(map);
    }
}

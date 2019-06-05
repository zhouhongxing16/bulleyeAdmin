package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import com.chris.bulleyeadmin.system.service.AccountRoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "accountrole", description = "帐号角色管理")
@RestController
@RequestMapping("/accountrole")
public class AccountRoleController extends BaseController<AccountRole> {

    @Autowired
    AccountRoleService accountRoleService;

    @Override
    public BaseService<AccountRole> getService() {
        return accountRoleService;
    }

    @RequestMapping("/saveAccountRoles")
    public JsonResult saveAccountRoles(@RequestBody Map<String,String> map){
       return accountRoleService.saveAccountRoles(map);
    }
}

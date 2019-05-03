package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.AccountRole;
import com.chris.bulleyeadmin.system.service.AccountRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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

    @Override
    public String getViewPrefix() {
        return "accountrole";
    }
}

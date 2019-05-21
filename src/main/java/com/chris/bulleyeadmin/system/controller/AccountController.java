package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.system.service.AccountService;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.RoleService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-11 12:00
 */
@OperationLog("帐号管理")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account> {

    @Autowired
    private AccountService accountService;

    @Override
    public BaseService<Account> getService() {
        return accountService;
    }


    @Override
    @OperationLog("创建账号")
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(Account obj) throws Exception {
        String password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.getUsername());
        obj.setPassword(password);
        return getService().add(obj);
    }
}

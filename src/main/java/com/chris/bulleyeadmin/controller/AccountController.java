package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.pojo.Account;
import com.chris.bulleyeadmin.pojo.JsonResult;
import com.chris.bulleyeadmin.service.AccountService;
import com.chris.bulleyeadmin.service.BaseService;
import com.chris.bulleyeadmin.service.RoleService;
import com.chris.bulleyeadmin.utils.Operalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-11 12:00
 */
@Controller
@RequestMapping("/account")
@Operalog("帐号")
public class AccountController extends BaseController<Account> {

    @Autowired
    private AccountService accountService;

    @Autowired
    private RoleService roleService;

    @Override
    public BaseService<Account> getService() {
        return accountService;
    }

    @Override
    public String getViewPrefix() {
        return "account";
    }

    @Operalog("登陆首页")
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(Model view) {
        Account account = new Account();
        account.setEmail("961860916@qq.com");
        accountService.add(account);
        view.addAttribute("user","");
        return "index";
    }

    //返回所有角色
    @PostMapping("/getAllRole")
    @ResponseBody
    public JsonResult getAllRole(){
       return null;
    }

    //通过账户Id获取对应角色
    @PostMapping("/getRoleByAccountId/{id}")
    @ResponseBody
    public JsonResult getRoleByAccountId(@PathVariable String id){
        return  null;
    }
}

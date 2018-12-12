package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.pojo.Account;
import com.chris.bulleyeadmin.pojo.User;
import com.chris.bulleyeadmin.security.MyInvocationSecurityMetadataSourceService;
import com.chris.bulleyeadmin.service.AccountService;
import com.chris.bulleyeadmin.utils.AuthUtil;
import com.chris.bulleyeadmin.utils.Operalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/***
 *
 */
@Operalog("登陆")
@Controller
public class LoginController {


    @Autowired
    private AccountService accountService;


    @RequestMapping(value = "/welcome", method = RequestMethod.GET)
    public String home() {
        return "welcome";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage()  {
        return "login";
    }

    @Operalog("进入首页")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(Model view) {
        //授权校验
        /*if(!licenseCheck()) {
            return "setup";
        }*/
        Account account = new Account();
        account.setEmail("961860916@qq.com");
        account.setPassword(PasswordEncoderFactories.createDelegatingPasswordEncoder().encode("1"));
        accountService.add(account);
        User user = AuthUtil.getCurrentUser();

        System.out.println("当前登陆用户："+user.getUsername());
        view.addAttribute("user","");

        return "index";
    }
}

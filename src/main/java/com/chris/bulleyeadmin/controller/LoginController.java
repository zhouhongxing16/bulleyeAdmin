package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.pojo.Account;
import com.chris.bulleyeadmin.pojo.User;
import com.chris.bulleyeadmin.service.AccountService;
import com.chris.bulleyeadmin.utils.AuthUtil;
import com.chris.bulleyeadmin.utils.Operalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
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
@Api("swaggerDemoController相关的api")
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
    @ApiOperation(value = "根据id查询学生信息", notes = "查询数据库中某个的学生信息")
    @ApiImplicitParam(name = "id", value = "学生ID", paramType = "path", required = true, dataType = "Integer")
    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String index(Model view) {
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

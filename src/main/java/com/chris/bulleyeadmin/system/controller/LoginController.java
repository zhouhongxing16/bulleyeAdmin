package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.AccountService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.common.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/***
 *
 */
@OperationLog("登陆")
@RestController
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

    @ResponseBody
    @GetMapping(value={"/imagevcode"}, produces={"image/jpeg"})
    public byte[] validateCodeImage(HttpServletRequest request) throws IOException
    {
        BufferedImage image = ValidateCodeUtils.getValidateCodeImage(request.getSession());
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(image, "jpg", baos);
        return baos.toByteArray();
    }

    @OperationLog("进入首页")
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

        return "index1";
    }
}

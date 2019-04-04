package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.Email;
import com.chris.bulleyeadmin.system.service.EmailService;
import com.chris.bulleyeadmin.system.service.MailSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-07-20 13:50
 */
@RestController
@RequestMapping("/admin/sys/email")
public class EmailController extends BaseController<Email> {

    @Autowired
    EmailService emailService;

    @Autowired
    MailSendService mailSendService;

    @Override
    public BaseService<Email> getService() {
        return emailService;
    }

    @Override
    public String getViewPrefix() {
        return "email";
    }

    @Override
    @ResponseBody
    @RequestMapping("/create")
    public JsonResult create(Email obj) throws Exception {

        obj.setSender("chris@zhouhongxing.cn");
        obj.setStatus(1);
        obj.setId(null);
        JsonResult msg = emailService.add(obj);
        if (msg.isSuccess()){
            mailSendService.sendHtmlEmail(obj.getAddressee(),obj.getSubject(),obj.getContent());
            msg.setMessage("发送成功");
            msg.setSuccess(true);
            return  msg;
        }else{
            msg.setMessage("发送失败！");
            msg.setSuccess(false);
            return  msg;
        }
    }
}

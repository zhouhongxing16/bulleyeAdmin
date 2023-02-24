package com.chris.bulleyeadmin.common.controller;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.MailSendService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Auther: Chris
 * @Date: 2019-05-27 16:38
 * @Description:
 */
@Api(tags = "mail",description = "邮件发送")
@OperationLog("邮件发送")
@RestController
@RequestMapping("/mail")
public class SendMailController {

    @Autowired
    MailSendService mailSendService;


    /**
     * 通用邮件发送接口
     *
     * @param
     * @return
     */
    @ApiOperation(value = "发送邮件", notes = "发送邮件")
    @ApiImplicitParam(name = "发送邮件", value = "参数:接收人recivers、主题subject、内容content")
    @PostMapping(value = "/sendMail")
    @ResponseBody
    @OperationLog("发送邮件")
    public Object sendMail(@RequestBody Map<String, String> map) {
        JsonResult result = new JsonResult();
        if (map.get("recivers") == null) {
            result.setSuccess(false);
            result.setMessage("接收人不能为空！");
            return result;
        } else if (map.get("subject") == null) {
            result.setSuccess(false);
            result.setMessage("主题不能为空！");
            return result;
        } else if (map.get("content") == null) {
            result.setSuccess(false);
            result.setMessage("邮件内容不能为空！");
            return result;
        } else {
            JSONArray jsonArray = JSON.parseArray(map.get("recivers"));
            String[] tos = new String[jsonArray.size()];
            for (int i = 0; i < jsonArray.size(); i++) {
                tos[i] = jsonArray.getString(i);
            }
            return mailSendService.sendEmail(tos, map.get("subject"), map.get("content"), map.get("filePath"));
        }

    }
}

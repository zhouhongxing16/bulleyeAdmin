package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.service.SendMessageService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.common.utils.ValidateCodeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-05-27 16:40
 * @Description:
 */
@Api(tags = "message",description = "短信发送")
@OperationLog("短信发送")
@RestController
@RequestMapping("/message")
public class SendMessageController {


    @Autowired
    SendMessageService sendMessageService;

    @ApiOperation(value = "发送短信", notes = "发送短信")
    @ApiImplicitParam(name = "发送短信", value = "参数:手机号mobiles、模版代码templateCode")
    @PostMapping("/send")
    @OperationLog("发送短信")
    public Object SendMessage(@RequestBody Map<String,Object> map) throws Exception {
        return sendMessageService.send(map);
    }

    @ApiOperation(value = "发送验证码", notes = "发送验证码")
    @ApiImplicitParam(name = "发送验证码", value = "参数:手机号mobiles")
    @PostMapping("/sendVerificationCode")
    @OperationLog("发送验证码")
    public Object sendVerificationCode(@RequestBody Map<String,Object> map, HttpServletRequest request) throws Exception {
        map.put("code", ValidateCodeUtils.getRandomValidateCode(request.getSession()));
        map.put("templateCode","VERIFICATION_CODE");
        return sendMessageService.send(map);
    }

    @PostMapping("/verificationCode")
    @OperationLog("验证验证码")
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @ApiImplicitParam(name = "验证验证码", value = "参数:手机号mobiles，验证码code")
    public Object verificationCode(@RequestBody Map<String,Object> map, HttpServletRequest request) throws Exception {
        String code = map.get("code").toString();
        if (code.equals(ValidateCodeUtils.getValidateCode(request.getSession()))){
            return true;
        }else {
            return false;
        }

    }

}

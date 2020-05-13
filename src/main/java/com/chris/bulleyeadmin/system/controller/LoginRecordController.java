package com.chris.bulleyeadmin.system.controller;
import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.LoginRecord;

import com.chris.bulleyeadmin.system.service.LoginRecordService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-17 18:00
 */
@Api(tags = "登录日志", produces = "登录日志")
@OperationLog("登录日志")
@RestController
@RequestMapping("/loginrecord")
public class LoginRecordController extends BaseController<LoginRecord> {

    @Autowired
    LoginRecordService loginRecordService;

    @Override
    public BaseService<LoginRecord> getService() {
        return loginRecordService;
    }
}

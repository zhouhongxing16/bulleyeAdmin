package com.chris.bulleyeadmin.system.controller;
import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;

import com.chris.bulleyeadmin.system.service.LogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-04-19 13:25
 */
@RestController
@RequestMapping("/log")
public class LogsController extends BaseController {

    @Autowired
    LogsService logsService;

    @Override
    public BaseService getService() {
        return logsService;
    }
}
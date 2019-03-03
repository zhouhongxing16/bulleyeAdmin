package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:16
 * @Description:
 */
@Controller
@RequestMapping("/wxaccount")
public class WxAccountController extends BaseController<WxAccount> {
    @Autowired
    WxAccountService wxAccountService;

    @Override
    public BaseService<WxAccount> getService() {
        return wxAccountService;
    }

    @Override
    public String getViewPrefix() {
        return "wxaccount";
    }
}

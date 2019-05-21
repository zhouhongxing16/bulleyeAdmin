package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.service.WxAccountService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:16
 * @Description:
 */
@OperationLog("公众号管理")
@RestController
@RequestMapping("/wxaccount")
public class WxAccountController extends BaseController<WxAccount> {
    @Autowired
    WxAccountService wxAccountService;

    //增加
    @OperationLog("增加")
    @PostMapping("/create")
    @Override
    public JsonResult create(@RequestBody WxAccount act)  throws Exception {
        if(StringUtils.isBlank(act.getMenuState())){
            act.setMenuState(UUID.randomUUID().toString());
        }
        return super.create(act);
    }

    @Override
    public BaseService<WxAccount> getService() {
        return wxAccountService;
    }
}

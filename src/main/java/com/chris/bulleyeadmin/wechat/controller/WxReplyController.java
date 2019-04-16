package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;
import com.chris.bulleyeadmin.wechat.service.WxReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:18
 * @Description:
 */
@OperationLog("微信自动回复")
@RestController
@RequestMapping("/wxreply")
public class WxReplyController extends BaseController<WxReply> {

    @Autowired
    WxReplyService wxReplyService;

    @Override
    public BaseService<WxReply> getService() {
        return wxReplyService;
    }

    @Override
    public String getViewPrefix() {
        return "wxreply";
    }
}

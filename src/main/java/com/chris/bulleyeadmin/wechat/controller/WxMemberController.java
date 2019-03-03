package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:15
 * @Description:
 */
@Controller
@RequestMapping("/wxmember")
public class WxMemberController extends BaseController<WxMember> {

    @Autowired
    WxMemberService wxMemberService;

    @Override
    public BaseService<WxMember> getService() {
        return wxMemberService;
    }

    @Override
    public String getViewPrefix() {
        return "wxmember";
    }
}

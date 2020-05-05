package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMember;
import com.chris.bulleyeadmin.wechat.service.WxMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:15
 * @Description:
 */
@OperationLog("公众号会员管理")
@RestController
@RequestMapping("/wxmember")
public class WxMemberController extends BaseController<WxMember> {

    @Autowired
    WxMemberService wxMemberService;

    @Override
    public BaseService<WxMember> getService() {
        return wxMemberService;
    }

    /*@OperationLog("设置用户分组")
    @GetMapping("/setTag")
    public JsonResult setTag(@RequestBody Map<String, Object> params){
        String sourceId = params.get("sourceId").toString();
        List<String> openids = Arrays.asList(params.get("openids").toString().split(","));
        return wxMemberService.setTag(sourceId, openids);
    }*/
}

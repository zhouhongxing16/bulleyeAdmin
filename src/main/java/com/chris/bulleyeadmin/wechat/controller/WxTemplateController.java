package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.pojo.WxTemplate;
import com.chris.bulleyeadmin.wechat.service.WxAccountService;
import com.chris.bulleyeadmin.wechat.service.WxTemplateService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplate;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@OperationLog("微信消息模板")
@RestController
@AllArgsConstructor
@RequestMapping("/wxtemplate")
public class WxTemplateController extends BaseController<WxTemplate> {

    @Autowired
    private final WxMpService wxMpService;

    @Autowired
    private WxTemplateService wxTemplateService;

    @Autowired
    private WxAccountService wxAccountService;

    @Override
    public BaseService<WxTemplate> getService() { return wxTemplateService; }

    @ApiOperation(value = "获取列表", notes = "获取列表")
    @OperationLog("获取列表")
    @GetMapping("/getAllPrivateTemplate/{sourceId}")
    public JsonResult getAllPrivateTemplate(@PathVariable String sourceId) {
        //获取相关接口
        WxAccount queryAccount = new WxAccount();
        queryAccount.setSourceId(sourceId);

        WxAccount account = wxAccountService.getMapper().selectOne(queryAccount);
        WxMpService wxService = this.wxMpService.switchoverTo(account.getAppId());

        try {
            List<WxMpTemplate> wxMpTemplateList = wxService.getTemplateMsgService().getAllPrivateTemplate();
            System.out.println("wxMpTemplateList："+wxMpTemplateList);

            wxTemplateService.insertByList(wxMpTemplateList, sourceId);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }

        return new JsonResult(true,null,"获取成功",null, HttpStatus.OK.value());
    }

    @ApiOperation(value = "测试推送", notes = "获取列表")
    @OperationLog("测试推送")
    @GetMapping("/testPushMessage")
    public JsonResult testPushMessage() {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("")
                .templateId("")
                .url("")
                .build();
        templateMessage.addData(new WxMpTemplateData("name1", "value1", "color2"));
        templateMessage.addData(new WxMpTemplateData("name2", "value2", "color2"));
        WxMpService wxService = this.wxMpService.switchoverTo("account.getAppId()");
        try {
            wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
        return new JsonResult(true,null,"获取成功",null, HttpStatus.OK.value());
    }

}

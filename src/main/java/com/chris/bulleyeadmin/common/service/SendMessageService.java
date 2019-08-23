package com.chris.bulleyeadmin.common.service;


import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.SendCode;
import com.chris.bulleyeadmin.common.entity.SendCodeEnum;
import com.chris.bulleyeadmin.common.utils.SendSMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class SendMessageService {

    @Autowired
    SendCode sendCode;

    public Object send(Map<String, String> params,String templateCode, String mobiles) throws Exception {
        JsonResult result = new JsonResult();
        if (mobiles == null) {
            result.setSuccess(false);
            result.setMessage("手机号不能为空！");
            return result;
        } else if (templateCode == null) {
            result.setSuccess(false);
            result.setMessage("模版Code不能为空！");
            return result;
        } else {
            params.remove("mobiles");
            result = SendSMSUtil.sendSMS(params, templateCode, mobiles, sendCode);
            return result;

        }
    }
}

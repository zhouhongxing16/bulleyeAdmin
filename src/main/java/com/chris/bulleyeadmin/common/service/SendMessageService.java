package com.chris.bulleyeadmin.common.service;


import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.SendCode;
import com.chris.bulleyeadmin.common.entity.SendCodeEnum;
import com.chris.bulleyeadmin.common.utils.SendSMSUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SendMessageService {

    @Autowired
    SendCode sendCode;

    public Object send(Map<String,Object> map) throws Exception {
        JsonResult result = new JsonResult();
        if(map.get("mobiles")==null){
            result.setSuccess(false);
            result.setMessage("手机号不能为空！");
            return result;
        }else if(map.get("templateCode")==null){
            result.setSuccess(false);
            result.setMessage("模版Code不能为空！");
            return result;
        }else{
            String templateCode = map.get("templateCode").toString();
                SendCodeEnum codeEnum = SendCodeEnum.getEnumValue(templateCode);
                if(codeEnum==null){
                    result.setSuccess(false);
                    result.setMessage("模版Code错误！");
                    return result;
                }else{
                    String mobiles = map.get("mobiles").toString();
                    result =  SendSMSUtil.sendSMS(map,mobiles, templateCode,sendCode);
                    return result;
                }

        }
    }
}

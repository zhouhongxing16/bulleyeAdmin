package com.chris.bulleyeadmin.common.service;


import com.alibaba.fastjson.JSONObject;
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
            String code = map.get("templateCode").toString();
            SendCodeEnum codeEnum = SendCodeEnum.getEnumValue(code);
            if(code==null){
                result.setSuccess(false);
                result.setMessage("模版Code错误！");
                return result;
            }else{
                String mobiles = map.get("mobiles").toString();
                JSONObject jo =  SendSMSUtil.sendSMS(map,mobiles, codeEnum.name(),sendCode);
                if("OK".equals(jo.get("Code"))){
                    result.setSuccess(false);
                    result.setMessage("短信发送成功！");
                }else{
                    System.out.println(jo.toJSONString());
                    result.setSuccess(false);
                    result.setMessage(jo.toJSONString());
                }
                return result;
            }
        }
    }
}

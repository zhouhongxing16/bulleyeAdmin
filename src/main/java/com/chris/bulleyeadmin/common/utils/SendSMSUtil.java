package com.chris.bulleyeadmin.common.utils;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.CommonResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.SendCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import org.json.JSONException;

import javax.xml.ws.http.HTTPException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SendSMSUtil {

    private static SendSMSUtil ourInstance = new SendSMSUtil();

    public static SendSMSUtil getInstance() {
        return ourInstance;
    }

    //验证码模版ID
    public static String SMS_VERIFICATION = "VERIFICATION_CODE";


    public static Integer getTencentSMSTemplateId(String templateCode) {
        switch (templateCode) {
            case "VERIFICATION_CODE":
                return 402871;
            default:
                return 0;
        }
    }

    public static String getAliyunSMSTemplateId(String templateCode) {
        switch (templateCode) {
            case "VERIFICATION_CODE":
                return "SMS_122282577";
            default:
                return "";
        }
    }

    private SendSMSUtil() {
    }

    public static JsonResult sendSMS(Map<String, String> params, String templateCode, String mobiles, SendCode sendCode) throws Exception {
        JsonResult jr;
        if ("Tencent".equals(sendCode.getDefaultSMS())) {
            List<String> list = new ArrayList<String>(params.values());
            String[] array = new String[list.size()];
            jr = sendTencentSMS(list.toArray(array), mobiles, getTencentSMSTemplateId(templateCode), sendCode);
        } else {
            jr = sendAliyunSMS(params, mobiles, getAliyunSMSTemplateId(templateCode), sendCode);
        }
        return jr;

    }


    public static JsonResult sendAliyunSMS(Map<String, String> params, String mobiles, String templateCode, SendCode sendCode) throws Exception {
        JsonResult jr = new JsonResult();

        DefaultProfile profile = DefaultProfile.getProfile("default", sendCode.getAccessKeyId(), sendCode.getAccessKeySecret());
        IAcsClient client = new DefaultAcsClient(profile);

        CommonRequest request = new CommonRequest();
        //request.setProtocol(ProtocolType.HTTPS);
        request.setMethod(MethodType.POST);
        request.setDomain(sendCode.getDomain());
        request.setVersion("2017-05-25");
        request.setAction("SendSms");
        request.putQueryParameter("PhoneNumbers", mobiles);
        request.putQueryParameter("SignName", sendCode.getSignName());
        request.putQueryParameter("TemplateCode", templateCode);
        ObjectMapper jsonMapper = new ObjectMapper();
        String str = jsonMapper.writeValueAsString(params);
        System.out.println(str);
        request.putQueryParameter("TemplateParam", str);

        try {
            CommonResponse response = client.getCommonResponse(request);
            JSONObject jo = JSONObject.parseObject(response.getData());
            if ("OK".equals(jo.getString("Code"))) {
                jr.setSuccess(true);
                jr.setMessage("短信发送成功");
            } else {
                jr.setSuccess(false);
                jr.setMessage("短信发送失败！");
            }
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return jr;
    }

    public static JsonResult sendTencentSMS(String[] params, String mobiles, Integer templateCode, SendCode sendCode) throws Exception {
        JsonResult jr = new JsonResult();
        try {

            SmsSingleSender ssender = new SmsSingleSender(sendCode.getAppId(), sendCode.getAppKey());
            SmsSingleSenderResult result = ssender.sendWithParam("86", mobiles, templateCode, params, sendCode.getSmsSign(), "", "");  // 签名参数未提供或者为空时，会使用默认签名发送短信
            if (result.result == 0) {
                jr.setSuccess(true);
                jr.setMessage(result.errMsg);
            } else {
                jr.setSuccess(false);
                jr.setMessage(result.errMsg);
            }

            System.out.println(result);
        } catch (HTTPException e) {
            // HTTP 响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // JSON 解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络 IO 错误
            e.printStackTrace();
        }
        return jr;
    }

}

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
import com.chris.bulleyeadmin.common.entity.SendCode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class SendSMSUtil {

    private static SendSMSUtil ourInstance = new SendSMSUtil();

    public static SendSMSUtil getInstance() {
        return ourInstance;
    }

    private SendSMSUtil() {
    }

    public static JSONObject sendSMS(Map<String,Object> map, String mobiles, String templateCode, SendCode sendCode) throws Exception {
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
        String str =  jsonMapper.writeValueAsString(map);
        System.out.println(str);
        request.putQueryParameter("TemplateParam", str);
        JSONObject jo = null;
        try {
            CommonResponse response = client.getCommonResponse(request);
            jo = JSON.parseObject(response.getData());
            System.out.println(response.getData());
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
        return jo;
    }

}

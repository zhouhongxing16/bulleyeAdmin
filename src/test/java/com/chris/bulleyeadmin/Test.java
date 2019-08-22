package com.chris.bulleyeadmin;

import com.alibaba.fastjson.JSONException;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;
import java.io.IOException;

/**
 * @Auther: Chris
 * @Date: 2019-02-14 17:20
 * @Description:
 */
public class Test {
    public static void main(String[] args){
        System.out.println(IPUtils.getLocationByIP("218.88.113.146"));
        try {
            // 短信应用SDK AppID
            // 短信应用SDK AppKey
            String appkey = "02Rs1BwHg5cQcdbIRrpvhkSXIxC7J2";
            int appid = 1400047724;
            // 需要发送短信的手机号码
            String[] phoneNumbers = { "13258179872" };
            // 短信模板ID，需要在短信应用中申请
            int templateId = 394263; // NOTE: 这里的模板ID`7839`只是一个示例，真实的模板ID需要在短信控制台中申请
            // 签名
            String smsSign = "码神联盟"; // NOTE:
            // 这里的签名"腾讯云"只是一个示例，真实的签名需要在短信控制台中申请，另外签名参数使用的是`签名内容`，而不是`签名ID`
            String[] params = {"9527"};
            SmsSingleSender ssender = new SmsSingleSender(appid, appkey);
            SmsSingleSenderResult result = null;
            try {
                result = ssender.sendWithParam("86", phoneNumbers[0],templateId, params,smsSign,"", "");
            } catch (org.json.JSONException e) {
                e.printStackTrace();
            }
            System.out.println(result);
        } catch (HTTPException e) {// HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {// json解析错误
            e.printStackTrace();
        } catch (IOException e) {// 网络IO错误
            e.printStackTrace();
        }
    }
}

package com.chris.bulleyeadmin.common.utils;


import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class SendSMSUtil {
    private static SendSMSUtil ourInstance = new SendSMSUtil();

    public static SendSMSUtil getInstance() {
        return ourInstance;
    }

    private SendSMSUtil() {
    }


    //产品名称:云通信短信API产品,开发者无需替换
    static final String product = "Dysmsapi";
    //产品域名,开发者无需替换
    static final String domain = "dysmsapi.aliyuncs.com";

    //科室报到通知短信模版code
    public static final String SECTIONCHECKINCODE = "SMS_133972087";

    //验证码通知短信模版code
    public static final String VERIFICATIONCODE = "SMS_80300315";

    //选课通知短信模版code
    public static final String STUDENTELECTIVECOURSE = "SMS_137550352";

    //面试通知短信模版code
    //{name}，您好： 请于${startDate}到${hospitalName}的${address}，参加我院组织的《住院医师规范化培训》的面试。特此通知
    public static final String TRAININTERVIEWCODE = "SMS_139971314";


    //消息通知模块
    //${name}，您好： 您有一条关于${title}的通知，详细内容请登录系统查看，请尽快处理。谢谢！
    public static final String MESSAGENOTICECODE = "SMS_138075177";

    // TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
    private static  String accessKeyId ;

    private static  String accessKeySecret;

    private static String signName;

    /**
     * 验证码短信
     * @return
     * @throws ClientException
     */
    public static SendSmsResponse verificationCode(String code,String mobile) throws Exception {

        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(VERIFICATIONCODE);
        //验证码${code}，您正在进行身份验证，打死不要告诉别人哦！
        /*ObjectMapper jsonMapper = new ObjectMapper();
        String str =  jsonMapper.writeValueAsString(map);*/
        request.setTemplateParam("{\"code\":'"+code+"'}");
        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");
        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");
        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
        return sendSmsResponse;
    }

    /**
     * 通用短信发送接口
     * @param map
     * @param mobile
     * @param templateCode
     * @return
     * @throws Exception
     */
    public static SendSmsResponse sendSMS(Map<String,Object> map,String mobile,String templateCode) throws Exception {

        System.out.println(accessKeyId);
        System.out.println(accessKeySecret);
        //可自助调整超时时间
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");

        //初始化acsClient,暂不支持region化
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        IAcsClient acsClient = new DefaultAcsClient(profile);

        //组装请求对象-具体描述见控制台-文档部分内容
        SendSmsRequest request = new SendSmsRequest();
        //必填:待发送手机号
        request.setPhoneNumbers(mobile);
        //必填:短信签名-可在短信控制台中找到
        request.setSignName(signName);
        //必填:短信模板-可在短信控制台中找到
        request.setTemplateCode(templateCode);
        //可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
        //${name}，您从${startdate}到${enddate}将转科到${section}，请在轮转开始日期当天准时到科室报到，谢谢！

        ObjectMapper jsonMapper = new ObjectMapper();
        String str =  jsonMapper.writeValueAsString(map);
        request.setTemplateParam(str);

        //选填-上行短信扩展码(无特殊需求用户请忽略此字段)
        //request.setSmsUpExtendCode("90997");

        //可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
        request.setOutId("yourOutId");

        //hint 此处可能会抛出异常，注意catch
        SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);

        return sendSmsResponse;
    }
    public static String getAccessKeyId() {
        return accessKeyId;
    }

    public static void setAccessKeyId(String accessKeyId) {
        SendSMSUtil.accessKeyId = accessKeyId;
    }

    public static String getAccessKeySecret() {
        return accessKeySecret;
    }

    public static void setAccessKeySecret(String accessKeySecret) {
        SendSMSUtil.accessKeySecret = accessKeySecret;
    }

    public static String getSignName() {
        return signName;
    }

    public static void setSignName(String signName) {
        SendSMSUtil.signName = signName;
    }
}

package com.chris.bulleyeadmin.wechat.utils;

import com.alibaba.fastjson2.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.File;
import java.io.FileInputStream;
import java.security.KeyStore;
import java.util.HashMap;
import java.util.Map;

public class WxPayUtil {

    /**
     * 统一下单
     *
     * @param appid            公众账号ID
     * @param appsecret        公众账号appsecret
     * @param mch_id           商户号
     * @param mch_password     商户号秘钥
     * @param body             商品描述
     * @param out_trade_no     商户订单号（要求32个字符内，只能是数字）
     * @param total_fee        标价金额 （单位为分）
     * @param spbill_create_ip 终端IP
     * @param time_start       交易起始时间（格式为yyyyMMddHHmmss）
     * @param time_expire      交易结束时间（格式为yyyyMMddHHmmss）
     * @param notify_url       通知地址
     * @param trade_type       交易类型 （JSAPI 公众号支付；NATIVE 扫码支付；APP APP支付；MWEB H5支付）
     * @param openid           公众号支付时，该参数必需
     * @return
     */
    public static Map<String, String> unifiedorder(String appid, String appsecret, String mch_id, String mch_password,
                                                   String body, String out_trade_no, String total_fee, String spbill_create_ip, String time_start, String time_expire,
                                                   String notify_url, String trade_type, String openid) {

        String serverUrl = "https://api.mch.weixin.qq.com/pay/unifiedorder";

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("mch_id", mch_id);
        paramMap.put("nonce_str", RandomStringUtils.random(16, Num62.N62_CHARS));
        paramMap.put("body", body);
        paramMap.put("out_trade_no", out_trade_no);
        paramMap.put("total_fee", total_fee);
        paramMap.put("spbill_create_ip", spbill_create_ip);
        if (StringUtils.isNotBlank(time_start) && StringUtils.isNotBlank(time_expire)) {
            paramMap.put("time_start", time_start);
            paramMap.put("time_expire", time_expire);
        }
        paramMap.put("notify_url", notify_url);
        paramMap.put("trade_type", trade_type);
        if (("NATIVE").equals(trade_type)) {//扫码支付
            paramMap.put("product_id", out_trade_no);
        }

        if ("MWEB".equals(trade_type)) {//H5支付
            JSONObject scene_info = new JSONObject();
            JSONObject object = new JSONObject();
            object.put("type", "Android");
            object.put("app_name", "ec");
            object.put("package_name", "com.cdtskj.ext.mall.util");
            scene_info.put("h5_info", object);
            paramMap.put("scene_info", scene_info.toJSONString());
        }

        if ("JSAPI".equals(trade_type)) {//公众号支付
            paramMap.put("openid", openid);
        }

        paramMap.put("sign", PayUtil.createSign(paramMap, mch_password));

        // 把参数转换成XML数据格式
        String xmlWeChat = PayUtil.assembParamToXml(paramMap);
        String resXml = HttpClientUtil.post(serverUrl, xmlWeChat);
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (StringUtils.isNotBlank(resXml)) {
                map = PayUtil.parseXMLToMap(resXml);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    /**
     * 查询订单
     *
     * @param appid          公众账号ID
     * @param appsecret      公众账号appsecret（不用）
     * @param mch_id         商户号
     * @param mch_password   商户号秘钥
     * @param transaction_id 微信订单号
     * @param out_trade_no   商户订单号
     * @return
     */
    public static Map<String, String> orderquery(String appid, String appsecret, String mch_id, String mch_password,
                                                 String transaction_id, String out_trade_no) {

        String serverUrl = "https://api.mch.weixin.qq.com/pay/orderquery";

        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("mch_id", mch_id);
        paramMap.put("nonce_str", RandomStringUtils.random(10, Num62.N62_CHARS));
//		paramMap.put("transaction_id", transaction_id);
        paramMap.put("out_trade_no", out_trade_no);
        paramMap.put("sign", PayUtil.createSign(paramMap, mch_password));

        // 把参数转换成XML数据格式
        String xmlWeChat = PayUtil.assembParamToXml(paramMap);
        String resXml = HttpClientUtil.post(serverUrl, xmlWeChat);
        Map<String, String> map = new HashMap<String, String>();
        try {
            if (StringUtils.isNotBlank(resXml)) {
                map = PayUtil.parseXMLToMap(resXml);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }


    /**
     * 通知微信成功
     */
    public static void noticeWeChatSuccess() {
        String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
        Map<String, String> parames = new HashMap<String, String>();
        parames.put("return_code", "SUCCESS");
        parames.put("return_msg", "OK");
        //将参数转成xml格式
        String xmlWeChat = PayUtil.assembParamToXml(parames);
        try {
            if (StringUtils.isNotBlank(url)) {
                String s = HttpClientUtil.post(url, xmlWeChat, "UTF-8");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 微信退款
     *
     * @param appid         公众账号ID
     * @param appsecret     公众账号appsecret
     * @param mch_id        商户号
     * @param mch_password  商户号秘钥
     * @param wx_file       证书文件
     * @param out_trade_no  商户订单号
     * @param out_refund_no 商户退款单号
     * @param total_fee     订单金额 （单位为分）
     * @param refund_fee    退款金额 （单位为分）
     * @return
     */
    public static Map<String, String> refund(String appid, String appsecret, String mch_id, String mch_password, String wx_file,
                                             String out_trade_no, String out_refund_no, String total_fee, String refund_fee) {
        String serverUrl = "https://api.mch.weixin.qq.com/secapi/pay/refund";
        Map<String, String> paramMap = new HashMap<String, String>();
        paramMap.put("appid", appid);
        paramMap.put("mch_id", mch_id);
        paramMap.put("nonce_str", RandomStringUtils.random(16, Num62.N62_CHARS));
        paramMap.put("out_trade_no", out_trade_no);
        paramMap.put("out_refund_no", out_refund_no);
        paramMap.put("total_fee", total_fee);
        paramMap.put("refund_fee", refund_fee);
        paramMap.put("sign", PayUtil.createSign(paramMap, mch_password));

        // 把参数转换成XML数据格式
        String xmlWeChat = PayUtil.assembParamToXml(paramMap);

        try {
            KeyStore keyStore = KeyStore.getInstance("PKCS12");
            FileInputStream instream = new FileInputStream(new File(wx_file));
            try {
                keyStore.load(instream, mch_id.toCharArray());
            } finally {
                instream.close();
            }
            // Trust own CA and all self-signed certs
            SSLContext sslcontext = SSLContexts.custom().loadKeyMaterial(keyStore, mch_id.toCharArray()).build();
            // Allow TLSv1 protocol only
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new String[]{"TLSv1"}, null,
                    SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
            CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(sslsf).build();
            try {
                HttpPost httpost = new HttpPost(serverUrl);
                httpost.setEntity(new StringEntity(xmlWeChat, "UTF-8"));
                CloseableHttpResponse response = httpclient.execute(httpost);
                try {
                    HttpEntity entity = response.getEntity();
                    String xmlStr = EntityUtils.toString(entity, "utf-8");
                    Map<String, String> map = new HashMap<String, String>();
                    map = PayUtil.parseXMLToMap(xmlStr);
                    return map;
                } finally {
                    response.close();
                    httpclient.close();
                }
            } finally {
                httpclient.close();
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
        return null;
    }
}

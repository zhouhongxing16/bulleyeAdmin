package com.chris.bulleyeadmin.wechat.controller;

import com.alibaba.fastjson.JSONObject;
import com.chris.bulleyeadmin.wechat.utils.PayUtil;
import com.chris.bulleyeadmin.wechat.utils.Tools;
import com.chris.bulleyeadmin.wechat.utils.WxPayUtil;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wxpay")
public class WxpayController {
	
	/**
	 * 微信支付统一接口 
	 * 需传递的参数除下方写出的，还必须包括   收款方:payee, 物品数量:num, 物品名称:goods_name, 成功后返回地址:return_url 
	 * 其他功能所需要的数据一并写在json中
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/pay")
	public String wxpay(HttpServletRequest request, HttpServletResponse response, ModelMap model ){
		JSONObject json = JSONObject.parseObject(request.getParameter("json"));//相关数据
		
		String body = json.get("body").toString();//商品描述
		String appid = json.get("appid").toString();//appid 公众账号ID
		String appsecret = json.get("appsecret").toString();//公众账号appsecret
		String partner = json.get("partner").toString();//商户号
		String partnerkey = json.get("partnerkey").toString();//商户号秘钥
		String order_code = json.get("order_code").toString();//商户订单号（要求32个字符内，只能是数字）
		Double total_fee = Double.valueOf(json.get("total_fee").toString());//标价金额 （单位为元）
		String openId = json.get("openId").toString();//支付用户的openid
		String notify_url = json.get("notify_url").toString();//通知地址
		
		model.put("json", json);
		
		Map<String, String> map = WxPayUtil.unifiedorder(appid, appsecret, partner, partnerkey,
				body, order_code, Tools.changeY2F(total_fee), Tools.getIpAddr(request),"", "",
				notify_url, "JSAPI",openId);
		
		System.out.println("微信公众号支付,统一下单返回结果map："+map.toString());
		
		//return_code 和result_code都为SUCCESS的时候有返回
		if(map.get("return_code").equals("SUCCESS") && map.get("result_code").equals("SUCCESS")){
			model.put("appId",map.get("appid"));//公众号id
			model.put("timeStamp",System.currentTimeMillis()/1000);//1414561699	当前的时间
			model.put("nonceStr",map.get("nonce_str"));//随机字符串，不长于32位。推荐随机数生成算法
			model.put("packages","prepay_id="+map.get("prepay_id"));//订单详情扩展字符串;prepay_id=123456789	统一下单接口返回的prepay_id参数值，提交格式如：prepay_id=***
			model.put("signType","MD5");//签名类型，默认为MD5，支持HMAC-SHA256和MD5。注意此处需与统一下单的签名类型一致
			//json.put("paySign",map.get("sign"));//签名
			Map<String, String> paramPaySign = new HashMap<String, String>();
			paramPaySign.put("appId", map.get("appid"));
			paramPaySign.put("timeStamp", System.currentTimeMillis()/1000+"");
			paramPaySign.put("nonceStr", map.get("nonce_str"));
			paramPaySign.put("package", "prepay_id="+map.get("prepay_id"));
			paramPaySign.put("signType", "MD5");
			model.put("paySign", PayUtil.createSign(paramPaySign, partnerkey));//签名
			
			model.put("flag", "true");
		}else{
			model.put("flag", "false");
		}
		//返回到支付界面
		return "";
	}
}

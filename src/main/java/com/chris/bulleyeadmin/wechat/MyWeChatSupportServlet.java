package com.chris.bulleyeadmin.wechat;

import com.chris.bulleyeadmin.wechat.pojo.WxMessageType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@Controller
@RequestMapping("/api/wechatauth")
public class MyWeChatSupportServlet {

    /**
     * 微信服务器配置校验
     */
    @RequestMapping(value = "/wx", method = RequestMethod.GET)
    public void wechatAuth(HttpServletRequest request, HttpServletResponse response) {

        String signature = request.getParameter("signature");
        String nonce = request.getParameter("nonce");
        String echostr = request.getParameter("echostr");
        String timestamp = request.getParameter("timestamp");

        PrintWriter out = null;
        try {
            out = response.getWriter();
            //校验成功返回echostr
            if (CheckUtil.checkSignature("myToken", signature, timestamp, nonce)) {
                out.write(echostr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 处理微信POST消息
     * @param request
     * @param response
     */
    @RequestMapping(value = "/wx", method = RequestMethod.POST)
    public void wx(HttpServletRequest request, HttpServletResponse response) {
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
        //将微信请求xml转为map格式，获取所需的参数
        Map<String, String> map = MessageUtil.xmlToMap(request);
        String ToUserName = map.get("ToUserName");
        String FromUserName = map.get("FromUserName");
        String MsgType = map.get("MsgType");
        String Content = map.get("Content");
        String event = map.get("Event");
        String eventKey = map.get("EventKey");

        //收到文本消息
        if(WxMessageType.MESSAGE_TYPE_TEXT.equals(MsgType)){

        }
    }

}

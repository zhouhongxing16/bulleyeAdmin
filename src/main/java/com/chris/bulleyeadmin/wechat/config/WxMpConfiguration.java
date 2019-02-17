package com.chris.bulleyeadmin.wechat.config;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.chris.bulleyeadmin.wechat.pojo.WxAccount;
import com.chris.bulleyeadmin.wechat.service.WxAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

import com.chris.bulleyeadmin.wechat.handler.KfSessionHandler;
import com.chris.bulleyeadmin.wechat.handler.LocationHandler;
import com.chris.bulleyeadmin.wechat.handler.LogHandler;
import com.chris.bulleyeadmin.wechat.handler.MenuHandler;
import com.chris.bulleyeadmin.wechat.handler.MsgHandler;
import com.chris.bulleyeadmin.wechat.handler.NullHandler;
import com.chris.bulleyeadmin.wechat.handler.ScanHandler;
import com.chris.bulleyeadmin.wechat.handler.StoreCheckNotifyHandler;
import com.chris.bulleyeadmin.wechat.handler.SubscribeHandler;
import com.chris.bulleyeadmin.wechat.handler.UnsubscribeHandler;
import com.google.common.collect.Maps;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpMessageRouter;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.constant.WxMpEventConstants;
import org.springframework.stereotype.Component;

import static me.chanjar.weixin.common.api.WxConsts.*;

/**
 * wechat mp configuration
 *
 * @author Binary Wang(https://github.com/binarywang)
 */
@Component
public class WxMpConfiguration implements CommandLineRunner {
    private LogHandler logHandler;
    private NullHandler nullHandler;
    private KfSessionHandler kfSessionHandler;
    private StoreCheckNotifyHandler storeCheckNotifyHandler;
    private LocationHandler locationHandler;
    private MenuHandler menuHandler;
    private MsgHandler msgHandler;
    private UnsubscribeHandler unsubscribeHandler;
    private SubscribeHandler subscribeHandler;
    private ScanHandler scanHandler;

    private static Map<String, WxMpMessageRouter> routers = Maps.newHashMap();
    private static Map<String, WxMpService> mpServices = Maps.newHashMap();

    @Autowired
    public WxMpConfiguration(LogHandler logHandler, NullHandler nullHandler, KfSessionHandler kfSessionHandler,
                             StoreCheckNotifyHandler storeCheckNotifyHandler, LocationHandler locationHandler,
                             MenuHandler menuHandler, MsgHandler msgHandler, UnsubscribeHandler unsubscribeHandler,
                             SubscribeHandler subscribeHandler, ScanHandler scanHandler) {
        this.logHandler = logHandler;
        this.nullHandler = nullHandler;
        this.kfSessionHandler = kfSessionHandler;
        this.storeCheckNotifyHandler = storeCheckNotifyHandler;
        this.locationHandler = locationHandler;
        this.menuHandler = menuHandler;
        this.msgHandler = msgHandler;
        this.unsubscribeHandler = unsubscribeHandler;
        this.subscribeHandler = subscribeHandler;
        this.scanHandler = scanHandler;
    }

    @Autowired
    WxAccountService wxAccountService;

    public static Map<String, WxMpMessageRouter> getRouters() {
        return routers;
    }

    public static Map<String, WxMpService> getMpServices() {
        return mpServices;
    }

    private WxMpMessageRouter newRouter(WxMpService wxMpService) {
        final WxMpMessageRouter newRouter = new WxMpMessageRouter(wxMpService);

        // 记录所有事件的日志 （异步执行）
        newRouter.rule().handler(this.logHandler).next();

        // 接收客服会话管理事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CREATE_SESSION)
            .handler(this.kfSessionHandler).end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_CLOSE_SESSION)
            .handler(this.kfSessionHandler)
            .end();
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.CustomerService.KF_SWITCH_SESSION)
            .handler(this.kfSessionHandler).end();

        // 门店审核事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(WxMpEventConstants.POI_CHECK_NOTIFY)
            .handler(this.storeCheckNotifyHandler).end();

        // 自定义菜单事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(MenuButtonType.CLICK).handler(this.menuHandler).end();

        // 点击菜单连接事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(MenuButtonType.VIEW).handler(this.nullHandler).end();

        // 关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(EventType.SUBSCRIBE).handler(this.subscribeHandler)
            .end();

        // 取消关注事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(EventType.UNSUBSCRIBE)
            .handler(this.unsubscribeHandler).end();

        // 上报地理位置事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(EventType.LOCATION).handler(this.locationHandler)
            .end();

        // 接收地理位置消息
        newRouter.rule().async(false).msgType(XmlMsgType.LOCATION)
            .handler(this.locationHandler).end();

        // 扫码事件
        newRouter.rule().async(false).msgType(XmlMsgType.EVENT)
            .event(EventType.SCAN).handler(this.scanHandler).end();

        // 默认
        newRouter.rule().async(false).handler(this.msgHandler).end();

        return newRouter;
    }

    @Override
    public void run(String... args) throws Exception {
        // 代码里 getConfigs()处报错的同学，请注意仔细阅读项目说明，你的getMpServicesIDE需要引入lombok插件！！！！
        //final List<WxMpProperties.MpConfig> configs =this.properties.getConfigs();
        final List<WxAccount> wxAccounts = wxAccountService.getAll();
        //final List<WxAccount> wxAccounts = new ArrayList<>();
        if (wxAccounts.size()==0) {
            System.out.println("数据库中无微信公众号相关信息");
            //throw new RuntimeException("数据库中无微信公众号相关信息");
        }
        mpServices = wxAccounts.stream().map(a -> {
            WxMpInMemoryConfigStorage configStorage = new WxMpInMemoryConfigStorage();
            configStorage.setAppId(a.getAppId());
            configStorage.setSecret(a.getAppSecret());
            configStorage.setToken(a.getToken());
            configStorage.setAesKey(a.getAesKey());

            WxMpService service = new WxMpServiceImpl();
            service.setWxMpConfigStorage(configStorage);
            routers.put(a.getAppId(), this.newRouter(service));
            return service;
        }).collect(Collectors.toMap(s -> s.getWxMpConfigStorage().getAppId(), a -> a, (o, n) -> o));
    }
}

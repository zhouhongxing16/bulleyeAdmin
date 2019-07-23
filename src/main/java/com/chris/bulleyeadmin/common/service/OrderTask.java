package com.chris.bulleyeadmin.common.service;

import com.chris.bulleyeadmin.system.pojo.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Created by: Chris on 2018-04-13 14:58
 * Description:
 */
@Component
public class OrderTask {


    @Scheduled(cron = "0,9,15 * * * * ? ")
    public void sendToBoss() throws Exception {
        Logger.debug("执行定时任务测试：" + new Date(System.currentTimeMillis()));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        Logger.debug("现在时间："+sdf.format(new Date()));

    }
}

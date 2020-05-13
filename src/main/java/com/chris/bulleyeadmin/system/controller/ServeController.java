package com.chris.bulleyeadmin.system.controller;


import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.monitor.Server;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "服务监控数据", produces = "服务监控数据")
@OperationLog("服务监控数据")
@RestController
@RequestMapping("/monitor")
public class ServeController {

    @OperationLog("获取服务监控数据")
    @GetMapping("/getInfo")
    public Object getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();
        return JsonResult.toSuccess(server,"获取服务器信息成功");
    }
}

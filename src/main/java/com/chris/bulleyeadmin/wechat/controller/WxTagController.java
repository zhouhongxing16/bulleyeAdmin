package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxTag;
import com.chris.bulleyeadmin.wechat.service.WxTagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:18
 * @Description:
 */
@OperationLog("微信用户标签")
@RestController
@RequestMapping("/wxtag")
public class WxTagController extends BaseController<WxTag> {

    @Autowired
    WxTagService wxTagService;

    @Override
    public BaseService<WxTag> getService() {
        return wxTagService;
    }
}

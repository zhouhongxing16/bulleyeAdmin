package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.service.WxMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@OperationLog("素材管理")
@RestController
@RequestMapping("/wxmaterial")
public class WxMaterialController extends BaseController<WxMaterial> {

    @Autowired
    private WxMaterialService wxMaterialService;

    @Override
    public BaseService<WxMaterial> getService() {
        return wxMaterialService;
    }

    @OperationLog("生成永久素材")
    @GetMapping("/materialUpload/{id}")
    public JsonResult materialUpload(@PathVariable String id) {
        return wxMaterialService.materialUpload(id);
    }

    @OperationLog("删除永久素材")
    @GetMapping("/materialDelete/{id}")
    public JsonResult materialDelete(@PathVariable String id) {
        return wxMaterialService.materialDelete(id);
    }

    @OperationLog("推送永久素材")
    @GetMapping("/pubMaterialToUser/{id}")
    public JsonResult pubMaterialToUser(@PathVariable String id) {
        return wxMaterialService.pubMaterialToUser(id);
    }
}

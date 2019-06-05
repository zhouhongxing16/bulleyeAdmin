package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.service.WxMaterialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @PostMapping("/materialUpload/{id}")
    public JsonResult materialUpload(@PathVariable String id){
        return wxMaterialService.materialUpload(id);
    }

    @OperationLog("删除永久素材")
    @PostMapping("/materialDelete/{id}")
    public JsonResult materialDelete(@PathVariable String id){
        return wxMaterialService.materialDelete(id);
    }

}

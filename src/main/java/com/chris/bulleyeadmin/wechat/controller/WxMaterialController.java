package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.service.WxMaterialService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/addMaterial")
    @ResponseBody
    public JsonResult addMaterial(@RequestBody WxMaterial obj) throws Exception {
        //新增时记录本地文件id
        String file_id= obj.getThumbMediaId();
        obj.setThumbFileId(file_id);
        obj.setThumbMediaId("");
        return super.create(obj);
    }

    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/updateMaterial")
    @ResponseBody
    public JsonResult updateMaterial(@RequestBody WxMaterial obj) throws Exception {
        //修改时记录本地文件id
        String file_id= obj.getThumbMediaId();
        obj.setThumbFileId(file_id);
        obj.setThumbMediaId("");
        return wxMaterialService.updateMaterial(obj);
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
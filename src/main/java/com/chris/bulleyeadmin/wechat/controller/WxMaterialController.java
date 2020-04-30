package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.service.WxMaterialService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

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

    @ApiOperation(value = "修改方法", notes = "创建")
    @ApiImplicitParam(name = "修改方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("修改方法")
    @PostMapping("/updateMaterial")
    @ResponseBody
    public JsonResult updateMaterial(@RequestBody WxMaterial obj) throws Exception {
        if (StringUtils.isNotBlank(obj.getMediaId())){
            return wxMaterialService.updateMaterial(obj);
        } else {
            return wxMaterialService.update(obj);
        }
    }

    //删除
    @ApiOperation(value = "根据ID删除一条数据", notes = "根据ID删除一条数据")
    @GetMapping("/delete/{id}")
    @ResponseBody
    @OperationLog("删除")
    public JsonResult remove(@PathVariable String id) {
        return getService().deleteById(id);
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

    @OperationLog("向指定用户推送永久素材")
    @GetMapping("/pubMaterialToUserList")
    public JsonResult pubMaterialToUserList(@RequestBody Map<String, Object> params) {
        String id = params.get("id").toString();
        List<String> openidList = Arrays.asList(params.get("openidList").toString().split(","));
        return wxMaterialService.pubMaterialToUserList(id, openidList);
    }

    @OperationLog("向所有用户推送永久素材")
    @GetMapping("/pubMaterialToUserAll/{id}")
    public JsonResult pubMaterialToUserAll(@PathVariable String id) {
        return wxMaterialService.pubMaterialToUserAll(id);
    }

    @OperationLog("搜索永久素材")
    @GetMapping("/getEverMaterialBySourceId/{sourceId}")
    public List<WxMaterial> getEverMaterial(@PathVariable String sourceId) {
        return wxMaterialService.getEverMaterialBySourceId(sourceId);
    }
}
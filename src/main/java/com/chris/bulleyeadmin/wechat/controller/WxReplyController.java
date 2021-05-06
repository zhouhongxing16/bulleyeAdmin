package com.chris.bulleyeadmin.wechat.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.PageResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.wechat.pojo.WxMaterial;
import com.chris.bulleyeadmin.wechat.pojo.WxReply;
import com.chris.bulleyeadmin.wechat.service.WxMaterialService;
import com.chris.bulleyeadmin.wechat.service.WxReplyService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Chris
 * @Date: 2019-03-03 14:18
 * @Description:
 */
@OperationLog("微信自动回复")
@RestController
@RequestMapping("/wxreply")
public class WxReplyController extends BaseController<WxReply> {

    @Autowired
    WxReplyService wxReplyService;

    @Autowired
    WxMaterialService wxMaterialService;

    @Override
    public BaseService<WxReply> getService() {
        return wxReplyService;
    }

    @ApiOperation(value = "默认分页查询", notes = "根据传递的参数进行查询")
    @ApiImplicitParam(name = "分页查询", value = "参数:{limit:number,pageSize:number,keyword:string}")
    @OperationLog("查询分页数据")
    @PostMapping("/listByPage")
    public Object listPage(@RequestBody Map<String, Object> params) {
        JsonResult result = new JsonResult();
        if(params.get("pageNum")==null){
            params.put("pageNum", "1");
        }
        if(params.get("pageSize")==null){
            params.put("pageSize", "10");
        }
        PageInfo info = getService().listByPage(params);
        for (WxReply wxReply: (List<WxReply>)info.getList()){
            if (StringUtils.isNotBlank(wxReply.getMaterialId())) {
                JsonResult json = wxMaterialService.getById(wxReply.getMaterialId());
                if (json.isSuccess()) {
                    WxMaterial wxMaterial = (WxMaterial)json.getData();
                    wxReply.setMaterialTitle(wxMaterial.getTitle());
                }
            }
        }

        PageResult<WxReply> pageResult = new PageResult<>();
        pageResult.setTotal(info.getTotal());
        pageResult.setList(info.getList());
        result.setStatus(HttpStatus.OK.value());
        result.setPage(pageResult);
        result.setSuccess(true);
        return result;
    }

    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    @Override
    public JsonResult create(@RequestBody WxReply obj) throws Exception {
        obj.setNum(0);
        Integer insertCount = wxReplyService.add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }

}

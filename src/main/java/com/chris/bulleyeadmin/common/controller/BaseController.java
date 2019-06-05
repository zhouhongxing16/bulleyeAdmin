package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * TODO
 *
 * @author Administrator
 * @date 2017/7/19.
 */
public abstract class BaseController<T> {

    public abstract BaseService<T> getService();

    @ApiOperation(value = "默认分页查询", notes = "根据传递的参数进行查询")
    @ApiImplicitParam(name = "分页查询", value = "参数:{limit:number,pageSize:number,keyword:string}")
    @OperationLog("查询分页数据")
    @PostMapping("/listByPage")
    @ResponseBody
    public Object listPage(@RequestBody Map<String, Object> params) {
        //根据不同的参数配置,有些传递的是offset
        params.put("pageNum", params.get("pageNum"));
        params.put("pageSize", params.get("pageSize"));
        params.put("keyword", params.get("keyword"));
        PageInfo info = getService().listByPage(params);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());

        return jsonMap;
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(@RequestBody T obj) throws Exception {
        return getService().add(obj);
    }

    //更新
    @ApiOperation(value = "更新一条数据", notes = "更新一条数据")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("更新")
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody T obj) {
        return getService().update(obj);
    }

    //删除
    @ApiOperation(value = "根据ID删除一条数据", notes = "根据ID删除一条数据")
    @GetMapping("/delete/{id}")
    @ResponseBody
    @OperationLog("删除")
    public JsonResult remove(@PathVariable String id) {
        return getService().deleteById(id);
    }

    //获取一条数据
    @ResponseBody
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据ID获取一条数据", notes = "根据ID获取一条数据")
    @OperationLog("获取一条数据")
    public Object getById(@PathVariable String id) {
        return getService().getById(id);
    }


    //获取一个list
    @ResponseBody
    @PostMapping("/getListByParams")
    @ApiOperation(value = "获取列表数据", notes = "获取列表数据")
    @ApiImplicitParam(name = "获取列表数据", value = "参数：任意参数")
    @OperationLog("获取列表数据")
    public JsonResult getListByParams(@RequestBody Map<String, Object> params) {
        List<T> list = getService().getListByParams(params);
        return new JsonResult(true, list, "查询成功！", null, HttpStatus.OK.value());
    }

    /*@InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/

}

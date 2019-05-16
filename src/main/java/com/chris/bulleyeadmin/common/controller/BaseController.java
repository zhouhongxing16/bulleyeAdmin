package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
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


    @ApiOperation(value = "默认分页查询",notes = "根据传递的参数进行查询")
    @OperationLog("查询分页数据")
    @RequestMapping("/listByPage")
    @ResponseBody
    public Object listPage(@RequestBody Map<String,Object> params) {
        //根据不同的参数配置,有些传递的是offset
        params.put("pageNum", params.get("pageNum"));
        params.put("pageSize",  params.get("pageSize"));
        params.put("keyword",  params.get("keyword"));
        PageInfo info = getService().listByPage(params);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());

        return jsonMap;
    }


    //增加
    @OperationLog("创建")
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(@RequestBody T obj)  throws Exception {
        return getService().add(obj);
    }

    //更新
    @OperationLog("更新")
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(@RequestBody T obj) {
        return getService().update(obj);
    }

    //删除
    @GetMapping("/delete/{id}")
    @ResponseBody
    @OperationLog("删除")
    public JsonResult remove(@PathVariable String id) {
        return getService().deleteById(id);
    }

    //获取一条数据
    @ResponseBody
    @GetMapping("/getById/{id}")
    @OperationLog("获取一条数据")
    public Object getById(@PathVariable String id) {
        return getService().getById(id);
    }

    //获取一个list
    @OperationLog("获取一个list")
    @ResponseBody
    @RequestMapping("/select")
    public Object select(@RequestBody T t) {
        //根据不同的参数配置,有些传递的是offset
        List<T> list = getService().select(t);
        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("list", list);
        return jsonMap;
    }


    //获取一个list
    @ResponseBody
    @RequestMapping("/getListByParams")
    @OperationLog("获取列表数据")
    public JsonResult getListByParams(@RequestBody Map<String,Object> params){
        List<T> list = getService().getListByParams(params);
        return new JsonResult(true,list,"查询成功！",null,HttpStatus.OK.value());
    }

    /*@InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/


}

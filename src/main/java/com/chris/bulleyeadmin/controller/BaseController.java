package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.pojo.JsonResult;
import com.chris.bulleyeadmin.pojo.Page;
import com.chris.bulleyeadmin.service.BaseService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


/**
 * TODO
 *
 * @author Administrator
 * @date 2017/7/19.
 */
public abstract class BaseController<T> {

    private ObjectMapper jsonMapper = new ObjectMapper();

    public abstract BaseService<T> getService();

    public abstract String getViewPrefix();

    @GetMapping(value = "/list")
    public String list(String menuId) {

        return getViewPrefix() + "/list";
    }

    @ApiOperation(value = "默认分页查询",notes = "根据传递的参数进行查询")
    @GetMapping("/listByPage")
    @ResponseBody
    public Object listPage(Page page,@RequestParam Map<String,String> params) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (page.getOffset()+ 1));
        map.put("pageSize", page.getLimit());
        map.put("keyword", page.getSearch());
        PageInfo info = getService().listByPage(map);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());

        return jsonMap;
    }

    @RequestMapping("/listPageById/{id}")
    @ResponseBody
    public Object listPageById(Page page,@PathVariable Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("pageNum", (page.getOffset()+ 1));
        map.put("pageSize", page.getLimit());
        map.put("keyword", page.getSearch());
        map.put("id",id);
        PageInfo info = getService().listPageById(map);

        Map<String,Object> jsonMap = new HashMap<>();
        jsonMap.put("rows", info.getList());
        jsonMap.put("total", info.getTotal());
        return jsonMap;
    }

    //进入添加页面
    @GetMapping("/add")
    public String add(String menuId, Model view) {
        return getViewPrefix() + "/add";
    }

    //增加
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(T obj)  throws Exception {
        boolean ret = getService().add(obj);
        return new JsonResult(ret);
    }

    //进入编辑页面
    @GetMapping("/edit/{id}")
    public String edit(Model view, @PathVariable String id) {

        T obj = findById(id);
        view.addAttribute(getViewPrefix(),obj);
        return getViewPrefix() + "/edit";
    }

    //进入编辑页面2
    @GetMapping("/edit")
    public String editEntity(Model view, T obj) {
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix() + "/edit";
    }

    //通过id查询信息
    public T findById(String id) {
        T msg = getService().getById(id);
        return msg;
    }

    //更新
    @PostMapping("/update")
    @ResponseBody
    public JsonResult update(T obj) {
        boolean ret = getService().update(obj);
        return new JsonResult(ret);
    }

    //进入详情页面
    @GetMapping("/view/{id}")
    public String view(Model view, @PathVariable String id) {
        T obj = findById(id);
        view.addAttribute("readonly","readonly");
        view.addAttribute(getViewPrefix(), obj);
        return getViewPrefix()  + "/view";
    }
    //删除
    @PostMapping("/remove")
    @ResponseBody
    public JsonResult deleteSection(@RequestParam String id) {
        boolean ret = getService().deleteById(id);
        return new JsonResult(ret);
    }

    //获取一条数据
    @ResponseBody
    @RequestMapping("/getById/{id}")
    public Object getById(@PathVariable String id) {
        return  getService().getById(id);
    }


}

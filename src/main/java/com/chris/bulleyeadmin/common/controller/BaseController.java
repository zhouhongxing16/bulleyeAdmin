package com.chris.bulleyeadmin.common.controller;

import com.chris.bulleyeadmin.common.entity.Constants;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.entity.PageResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.User;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

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
    public Object listPage(@RequestBody Map<String, Object> params) {
        JsonResult result = new JsonResult();
        if(params.get("pageNum")==null){
            params.put("pageNum", "1");
        }
        if(params.get("pageSize")==null){
            params.put("pageSize", "10");
        }
        PageInfo info = getService().listByPage(params);
        PageResult<T> pageResult = new PageResult<>();
        pageResult.setTotal(info.getTotal());
        pageResult.setList(info.getList());
        result.setStatus(HttpStatus.OK.value());
        result.setPage(pageResult);
        result.setSuccess(true);
        return result;
    }

    @ApiOperation(value = "默认分页查询", notes = "根据传递的参数进行查询")
    @ApiImplicitParam(name = "分页查询", value = "参数:{limit:number,pageSize:number,keyword:string}")
    @OperationLog("查询分页数据")
    @PostMapping("/listAuthByPage")
    public JsonResult<T> listAuthByPage(@RequestBody Map<String, Object> params) {
        PageInfo info = getService().listByPage(params);
        JsonResult<T> result = new JsonResult();
        PageResult<T> page = new PageResult<>();
        page.setList(info.getList());
        page.setTotal(info.getTotal());
        page.setLastPage(info.isIsLastPage());
        result.setPage(page);
        result.setMessage("查询成功");
        result.setSuccess(true);
        result.setStatus(HttpStatus.OK.value());
        return result;
    }


    //增加
    @ApiOperation(value = "创建方法", notes = "创建")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("创建方法")
    @PostMapping("/create")
    public JsonResult create(@RequestBody T obj) throws Exception {
        Integer insertCount = getService().add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }

    //更新
    @ApiOperation(value = "更新一条数据", notes = "更新一条数据")
    @ApiImplicitParam(name = "创建方法", value = "参数如果有时间字段请按照 yyyy-MM-dd hh:mm:ss 格式传入")
    @OperationLog("更新")
    @PostMapping("/update")
    public JsonResult update(@RequestBody T obj) {
        return getService().update(obj);
    }

    //删除
    @ApiOperation(value = "根据ID删除一条数据", notes = "根据ID删除一条数据")
    @GetMapping("/delete/{id}")
    @OperationLog("删除")
    public JsonResult remove(@PathVariable String id) {
        return getService().deleteById(id);
    }

    //获取一条数据
    @GetMapping("/getById/{id}")
    @ApiOperation(value = "根据ID获取一条数据", notes = "根据ID获取一条数据")
    @OperationLog("获取一条数据")
    public Object getById(@PathVariable String id) {
        return getService().getById(id);
    }
/*

    //获取一个list
    @PostMapping("/getListByParams")
    @ApiOperation(value = "获取列表数据", notes = "获取列表数据")
    @ApiImplicitParam(name = "获取列表数据", value = "参数：任意参数")
    @OperationLog("获取列表数据")
    public JsonResult getListByParams(@RequestBody Map<String, Object> params) {
        List<T> list = getService().getListByParams(params);
        return new JsonResult(true, list, "查询成功！", null, HttpStatus.OK.value());
    }*/

    //获取一个list
    @PostMapping("/getListByParams")
    @ApiOperation(value = "获取列表数据", notes = "获取列表数据")
    @ApiImplicitParam(name = "params", value = "参数：任意参数")
    @OperationLog("获取列表数据")
    public JsonResult<T> getListByParams(@RequestBody Map<String, Object> params) {
        JsonResult<T> result = new JsonResult();
        List<T> list = getService().getListByParams(params);
        result.setList(list);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        return result;
    }

    //获取一个list
    @PostMapping("/getAuthListByParams")
    @ApiOperation(value = "获取列表数据(带数据权限)", notes = "获取列表数据(带数据权限)")
    @ApiImplicitParam(name = "params", value = "参数：任意参数")
    @OperationLog("获取列表数据(带数据权限)")
    public JsonResult<T> getAuthListByParams(@RequestBody Map<String, Object> params) {
        JsonResult<T> result = new JsonResult();
        params.putAll(getAuthParameterMap(params));
        List<T> list = getService().getListByParams(params);
        result.setList(list);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        return result;
    }

    protected Map<String, Object> getAuthParameterMap(Map<String, Object> map) {
        User user = AuthUtil.getCurrentUser();
        Role currentRole = user.getCurrentRole();
        String authFlag = currentRole.getDataAuthFlag();
        map.put(Constants.ORGANIZATION_ID, user.getOrganizationId());
        if (currentRole != null) {
            if (Constants.ORGANIZATION.equals(authFlag)) {
                map.put(Constants.ORGANIZATION_ID, user.getOrganizationId());
            } else if (Constants.DEPARTMENT.equals(authFlag)) {
                map.put(Constants.DEPARTMENT_ID, user.getDepartmentId());
            } else if (Constants.PERSONAL.equals(authFlag)) {
                map.put(Constants.DEPARTMENT_ID, user.getDepartmentId());
                map.put(Constants.STAFF_ID, user.getDepartmentId());
            }
        }
        return map;
    }
    /*@InitBinder
    protected void initBinder(WebDataBinder binder)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }*/

}

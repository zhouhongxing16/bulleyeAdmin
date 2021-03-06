package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Department;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.DepartmentService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:57
 * @Description:
 */
@Api(tags = "部门管理", produces = "部门管理")
@OperationLog("部门管理")
@RestController
@RequestMapping("/department")
public class DepartmentController extends BaseController<Department> {

    @Autowired
    DepartmentService departmentService;

    @Override
    public BaseService<Department> getService() {
        return departmentService;
    }

    @Override
    @PostMapping("/create")
    @OperationLog("创建部门")
    public JsonResult create(@RequestBody Department obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setOrganizationId(user.getOrganizationId());
        obj.setUserId(user.getId());
        Integer insertCount = departmentService.add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }
}

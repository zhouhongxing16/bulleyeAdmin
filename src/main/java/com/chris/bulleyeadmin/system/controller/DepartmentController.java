package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.system.pojo.Department;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:57
 * @Description:
 */
@Controller
@RequestMapping("/department")
public class DepartmentController extends BaseController<Department> {

    @Autowired
    DepartmentService departmentService;

    @Override
    public BaseService<Department> getService() {
        return departmentService;
    }

    @Override
    public String getViewPrefix() {
        return "department";
    }
}

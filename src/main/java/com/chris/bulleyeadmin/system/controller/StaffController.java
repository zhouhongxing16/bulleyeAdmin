package com.chris.bulleyeadmin.system.controller;


import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.system.pojo.Staff;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Chris
 * @Date: 2018-11-30 16:43
 * @Description:
 */
@Controller
@RequestMapping("/staff")
public class StaffController extends BaseController<Staff> {

    @Autowired
    StaffService staffService;


    @Override
    public BaseService<Staff> getService() {
        return staffService;
    }

    @Override
    public String getViewPrefix() {
        return "staff";
    }

}

package com.chris.bulleyeadmin.system.controller;


import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Staff;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.StaffService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Chris
 * @Date: 2018-11-30 16:43
 * @Description:
 */
@Api(tags = "员工管理", produces = "员工管理")
@OperationLog("员工管理")
@RestController
@RequestMapping("/staff")
public class StaffController extends BaseController<Staff> {

    @Autowired
    StaffService staffService;


    @Override
    public BaseService<Staff> getService() {
        return staffService;
    }

    @GetMapping(value = "/getStaffInfo")
    @OperationLog("获取我的信息")
    public Object getStaffInfo(){
      User user =  AuthUtil.getCurrentUser();
      JsonResult jsonResult = staffService.getById(user.getStaffId());
      return  jsonResult;
    }

    @Override
    @PostMapping("/create")
    @OperationLog("创建员工")
    public JsonResult create(@RequestBody Staff obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setOrganizationId(user.getOrganizationId());
        Integer insertCount = staffService.add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }
}

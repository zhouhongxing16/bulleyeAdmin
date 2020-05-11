package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Organization;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.OrganizationService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:38
 * @Description:
 */
@Api(tags = "organization", description = "组织管理")
@OperationLog("组织管理")
@RestController
@RequestMapping("/organization")
public class OrganizationController extends BaseController<Organization> {

    @Autowired
    OrganizationService organizationService;


    @Override
    public BaseService<Organization> getService() {
        return organizationService;
    }

    @Override
    @PostMapping("/create")
    @ResponseBody
    public JsonResult create(@RequestBody Organization obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setUserId(user.getId());
        return organizationService.add(obj);
    }
}

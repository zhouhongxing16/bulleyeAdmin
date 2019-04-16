package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Organization;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:38
 * @Description:
 */
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
    public String getViewPrefix() {
        return "organization";
    }
}

package com.chris.bulleyeadmin.controller;

import com.chris.bulleyeadmin.pojo.Organization;
import com.chris.bulleyeadmin.service.BaseService;
import com.chris.bulleyeadmin.service.OrganizationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:38
 * @Description:
 */
@Controller
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

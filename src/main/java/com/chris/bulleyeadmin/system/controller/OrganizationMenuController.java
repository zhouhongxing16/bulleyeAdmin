package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.pojo.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.OrganizationMenu;
import com.chris.bulleyeadmin.system.service.OrganizationMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Chris
 * @Date: 2019-01-07 15:38
 * @Description:
 */
@OperationLog("组织菜单")
@RestController
@RequestMapping("/organizationmenu")
public class OrganizationMenuController extends BaseController<OrganizationMenu> {

    @Autowired
    OrganizationMenuService organizationMenuService;


    @Override
    public BaseService<OrganizationMenu> getService() {
        return organizationMenuService;
    }

    @Override
    public String getViewPrefix() {
        return "organizationmenu";
    }

    @PostMapping("/createOrganizationMenu")
    public JsonResult createOrganizationMenu(@RequestBody List<OrganizationMenu> list){
        return organizationMenuService.createOrganizationMenu(list);
    }
}

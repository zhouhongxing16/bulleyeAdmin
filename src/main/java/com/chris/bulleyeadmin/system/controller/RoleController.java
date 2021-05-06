package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.RoleService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:50
 */
@Api(tags = "角色管理", produces = "角色管理")
@OperationLog("角色管理")
@RestController
@RequestMapping("/role")
public class RoleController extends BaseController<Role> {

    @Autowired
    RoleService roleService;

    @Override
    public BaseService<Role> getService() {
        return roleService;
    }

    @Override
    @OperationLog("创建角色")
    @PostMapping("/create")
    public JsonResult create(Role obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setOrganizationId(user.getOrganizationId());
        obj.setUserId(user.getId());

        Integer insertCount = getService().add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }

    @OperationLog("获取所有角色")
    @RequestMapping("/getAllRoles")
    public JsonResult getAllRoles() {
        Map<String, Object> map = new HashMap<>(2);
        map.put("organizationId", AuthUtil.getCurrentUser().getOrganizationId());
        List<Role> roles = roleService.getListByParams(map);
        return new JsonResult(true, roles);
    }

    @OperationLog("获取账号所有角色")
    @RequestMapping("/getRolesByAccountId/{accountId}")
    public JsonResult getRolesByAccountId(@PathVariable String accountId) {
        List<Role> roles = roleService.getRolesByAccountId(accountId);
        return new JsonResult(true, roles);
    }


}

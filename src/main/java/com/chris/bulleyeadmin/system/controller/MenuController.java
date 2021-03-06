package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.AuthUtil;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.dto.MenuDto;
import com.chris.bulleyeadmin.system.pojo.Menu;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-13 9:48
 */
@Api(tags = "菜单管理", produces = "菜单管理")
@OperationLog("菜单管理")
@RestController
@RequestMapping("/menu")
public class MenuController extends BaseController<Menu> {

    @Autowired
    MenuService menuService;

    @Override
    public BaseService<Menu> getService() {
        return menuService;
    }

    @OperationLog("获取所有菜单")
    @GetMapping("/getAllMenus")
    public JsonResult<MenuDto> getAllMenus() {
        JsonResult<MenuDto> result = new JsonResult<>();
        List<MenuDto> menuList = menuService.getAllMenus();
        result.setList(menuList);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("查询成功！");
        return result;
    }

    @OperationLog("获取组织菜单")
    @PostMapping("/getOrganizationMenus")
    public JsonResult<MenuDto> getOrganizationMenus(@RequestBody Map<String, Object> map) {
        JsonResult<MenuDto> result = new JsonResult<>();
        if (map.get("organizationId") == null) {
            User user = AuthUtil.getCurrentUser();
            map.put("organizationId", user.getOrganizationId());
        }
        List<MenuDto> menuList = menuService.getOrganizationMenus(map);
        result.setList(menuList);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("查询成功！");
        return result;
    }


    @OperationLog("获取组织授权后菜单")
    @PostMapping("/getOrganizationAuthMenus")
    public JsonResult getOrganizationAuthMenus(@RequestBody Map<String, Object> map) {
        JsonResult<MenuDto> result = new JsonResult<>();
        User user = AuthUtil.getCurrentUser();
        map.put("organizationId", user.getOrganizationId());
        List<MenuDto> menuList = menuService.getOrganizationAuthMenus(map);

        result.setList(menuList);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("查询成功！");
        return result;
    }

    @OperationLog("获取登录用户菜单")
    @GetMapping("/getMenusByAccountId")
    public JsonResult getMenusByAccountId() {
        JsonResult<MenuDto> result = new JsonResult<>();
        List<MenuDto> menuList = menuService.getMenusByAccountId();

        result.setList(menuList);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("查询成功！");
        return result;
    }

    @OperationLog("根据角色获取菜单")
    @GetMapping("/getMenusByRoleId/{roleId}")
    public JsonResult getMenusByRoleId(@PathVariable String roleId) {
        JsonResult<MenuDto> result = new JsonResult<>();
        List<MenuDto> menuList = menuService.getMenusByRoleId(roleId);

        result.setList(menuList);
        result.setStatus(HttpStatus.OK.value());
        result.setSuccess(true);
        result.setMessage("查询成功！");
        return result;
    }

    @Override
    @OperationLog("创建菜单")
    @PostMapping("/create")
    public JsonResult create(@RequestBody Menu obj) throws Exception {
        User user = AuthUtil.getCurrentUser();
        obj.setUserId(user.getId());
        Integer insertCount = menuService.add(obj);
        String msg = insertCount > 0 ? "成功添加" + insertCount + "条记录" : "新增数据失败！";
        return new JsonResult(insertCount > 0 ? true : false, null, msg, null, HttpStatus.OK.value());
    }
}

package com.chris.bulleyeadmin.system.controller;
import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.common.utils.OperationLog;
import com.chris.bulleyeadmin.system.pojo.MenuAuth;
import com.chris.bulleyeadmin.system.service.MenuAuthService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * @Author: Chris  E-mail:961860916@qq.com
 * @Date:  2019-03-17 21:10
 */
@Api(tags = "菜单权限管理", produces = "菜单权限管理")
@OperationLog("菜单权限管理")
@RestController
@RequestMapping("/menuauth")
public class MenuAuthController extends BaseController<MenuAuth> {
    
    @Autowired
    private MenuAuthService menuAuthService;

     @Override
    public BaseService getService() {
        return menuAuthService;
    }
}

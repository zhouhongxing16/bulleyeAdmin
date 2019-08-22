package com.chris.bulleyeadmin.system.controller;

import com.chris.bulleyeadmin.common.controller.BaseController;
import com.chris.bulleyeadmin.common.utils.*;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.pojo.*;
import com.chris.bulleyeadmin.common.entity.JsonResult;
import com.chris.bulleyeadmin.system.service.AccountService;
import com.chris.bulleyeadmin.common.service.BaseService;
import com.chris.bulleyeadmin.system.service.LoginRecordService;
import com.chris.bulleyeadmin.system.service.RoleService;
import com.chris.bulleyeadmin.system.service.StaffService;
import eu.bitwalker.useragentutils.UserAgent;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-11 12:00
 */
@Api(tags = "account", description = "帐号管理")
@OperationLog("帐号管理")
@RestController
@RequestMapping("/account")
public class AccountController extends BaseController<Account> {

    @Autowired
    private AccountService accountService;

    @Autowired
    RoleService roleService;

    @Autowired
    StaffService staffService;

    @Autowired
    LoginRecordService loginRecordService;
    @Override
    public BaseService<Account> getService() {
        return accountService;
    }


    @Override
    @OperationLog("创建账号")
    @PostMapping("/create")
    public JsonResult create(Account obj) throws Exception {
        String password = PasswordEncoderFactories.createDelegatingPasswordEncoder().encode(obj.getUsername());
        obj.setPassword(password);
        return getService().add(obj);
    }




    @ApiOperation(value = "管理员登录", notes = "参数：用户名username，密码password")
    @OperationLog("管理员登录")
    @PostMapping("/adminLogin")
    public Object adminLogin(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        AccountDto accountDto = accountService.getAccountByUserName(username);
        return login(accountDto, username, password);
    }

    public JsonResult login(AccountDto accountDto, String username, String password) {
        JsonResult result = new JsonResult();
        if (accountDto != null && PasswordEncoderFactories.createDelegatingPasswordEncoder().matches(password, accountDto.getPassword())) {
            //判断账号过期
            if (accountDto.getExpiredDate() != null && accountDto.getAccountExpired()) {
                if (DateUtils.getNowDate().getTime() > accountDto.getExpiredDate().getTime()) {
                    accountDto.setAccountExpired(true);
                } else {
                    accountDto.setAccountExpired(false);
                }
            } else {
                accountDto.setAccountExpired(false);
            }
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            String studentId = null;
            Staff staff = null;

            if (StringUtils.isNotEmpty(accountDto.getStaffId())) {
                JsonResult jsonResult = staffService.getById(accountDto.getStaffId());
                staff = (Staff) jsonResult.getData();

            }
            //找到登录用户角色放到grantedAuthority中
            List<Role> roles = roleService.getRolesByAccountId(accountDto.getId());
            String rolestr = "";
            for (Role role : roles) {
                if (role != null && role.getCode() != null) {
                    rolestr = rolestr + "," + role.getCode();
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getCode());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            System.out.println("当前用户角色:" + rolestr);

            User user = new User(accountDto.getId(), accountDto.getUsername(), accountDto.getPassword(),accountDto.getOrganizationId(),staff.getId(),staff.getDepartmentId(),accountDto.getAccountLocked(),accountDto.getAccountExpired(),grantedAuthorities);
            System.out.println(grantedAuthorities);
            String onlineName = "";
            if (roles != null && roles.size() > 0) {
                onlineName += "(";
                /**
                 *for (Role r : roles) {
                 *	onlineName += r.getName();
                 *		break;
                 *    }
                 */
                onlineName += roles.get(0).getName();
                onlineName += ")";
            }
            result.setSuccess(true);
            result.setMessage("登录成功");
            result.setStatus(HttpStatus.OK.value());
            result.setData(JwtHelper.createJWT(user.toString()));
        } else {
            result.setSuccess(false);
            result.setMessage("用户名或密码错误！");
        }

        HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
        UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader("User-Agent"));
        // 获取客户端操作系统
        String os = userAgent.getOperatingSystem().getName();
        // 获取客户端浏览器
        String browser = userAgent.getBrowser().getName();

        new Thread(() -> {
            String ip = IPUtils.getIpAddr(request);
            LoginRecord loginRecord = new LoginRecord();
            loginRecord.setUsername(username);
            loginRecord.setIp(ip);
            loginRecord.setOs(os);
            loginRecord.setBrowser(browser);
            loginRecord.setLoginLocation(IPUtils.getLocationByIP(ip));
            loginRecord.setStatus(result.isSuccess() ? 1 : 0);
            loginRecord.setMessage(result.isSuccess() ? "登录成功" : "用户名或密码错误！");
            loginRecordService.add(loginRecord);
        }).start();

        return result;
    }
}

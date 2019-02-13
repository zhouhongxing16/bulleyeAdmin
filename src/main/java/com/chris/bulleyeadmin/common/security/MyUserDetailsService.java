package com.chris.bulleyeadmin.common.security;

import com.chris.bulleyeadmin.common.excepition.RPCFailedException;
import com.chris.bulleyeadmin.system.dto.AccountDto;
import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.Staff;
import com.chris.bulleyeadmin.system.pojo.User;
import com.chris.bulleyeadmin.system.service.AccountService;
import com.chris.bulleyeadmin.system.service.RoleService;
import com.chris.bulleyeadmin.system.service.StaffService;
import com.chris.bulleyeadmin.common.utils.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Chris E-mail:961860916@qq.com
 * @Date: 2018-06-22 13:41
 */
@Service
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    AccountService accountService;

    @Autowired
    StaffService staffService;

    @Autowired
    RoleService roleService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("用户的用户名: {}", username);
        return loadUser(username,null,null);
    }

    public UserDetails loadUser(String username, String pwd, String orgId){
        AccountDto accountDto;
        try {
            accountDto = accountService.getAccountByUserName( username);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            throw new RPCFailedException(e.getMessage());
        }
        if (accountDto != null) {
            //add by onion：设置账号过期
            if(accountDto.getExpiredDate() != null) {
                /*if (DateUtils.getNowDate().getTime() > account.getExpiredDate().getTime()) {
                    throw new RuntimeException("非常抱歉,您的试用账号已到期,请联系我们!");
                }*/

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (!encoder.matches(pwd, accountDto.getPassword())) {
                    logger.info("用户密码不正确...");
                    return null;
                }
            }
            //				Authority auth = authService.getAuthsByUserId(account.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            String departmentId = "";
            String staffId = accountDto.getStaffId();
            orgId = accountDto.getOrganizationId();
            if (StringUtils.isNotEmpty( staffId )) {
                Staff staff = staffService.getById( staffId );
                departmentId = staff.getDepartmentId();
            }

            //找到登录用户角色放到grantedAuthority中
            List<Role> roles = roleService.getRolesByAccountId( accountDto.getId() );
            String rolestr = "";
            for (Role role : roles) {
                if (role != null && role.getCode()!=null) {
                    rolestr = rolestr+","+role.getCode();
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getCode());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            System.out.println("当前用户角色:"+rolestr);

            User user = new User( accountDto.getId(),accountDto.getStaff(), accountDto.getUsername(), accountDto.getPassword(), orgId, staffId, departmentId, grantedAuthorities );
            if (StringUtils.isNotEmpty( accountDto.getOrganizationId() )) {
                //管理机构
                user.setOrganizationId( accountDto.getOrganizationId() );
            }

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
                onlineName += roles.get( 0 ).getName();
                onlineName += ")";
            }
            user.setRole( roles );
            user.setRoleName( onlineName );
            return user;
        } else {
            logger.info( "用户" + username + " 不存在" );
            throw new UsernameNotFoundException( "用户名或密码不正确" );
        }
    }
}

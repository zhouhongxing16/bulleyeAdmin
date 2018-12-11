package com.chris.bulleyeadmin.security;

import com.chris.bulleyeadmin.excepition.RPCFailedException;
import com.chris.bulleyeadmin.pojo.Account;
import com.chris.bulleyeadmin.pojo.Role;
import com.chris.bulleyeadmin.pojo.Staff;
import com.chris.bulleyeadmin.pojo.User;
import com.chris.bulleyeadmin.service.AccountService;
import com.chris.bulleyeadmin.service.RoleService;
import com.chris.bulleyeadmin.service.StaffService;
import com.chris.bulleyeadmin.utils.DateUtils;
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
        Account account;
        try {
            account = accountService.getAccountByUserName( username);
        } catch (Exception e) {
            throw new RPCFailedException();
        }
        if (account != null) {
            //add by onion：设置账号过期
            if(account.getExpiredDate() != null) {
                if (DateUtils.getNowDate().getTime() > account.getExpiredDate().getTime()) {
                    throw new RuntimeException("非常抱歉,您的试用账号已到期,请联系我们!");
                }

                BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
                if (!encoder.matches(pwd, account.getPassword())) {
                    logger.info("用户密码不正确...");
                    return null;
                }
            }
            //				Authority auth = authService.getAuthsByUserId(account.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

            String departmentId = "";
            String staffId = account.getStaffId();
            String staffName = account.getUsername();
            orgId = account.getOrgId();
            if (StringUtils.isNotEmpty( staffId )) {
                Staff staff = staffService.getById( staffId );
                departmentId = staff.getDepartmentId();
                staffName = (staff.getName());
            }

            //找到登录用户角色放到grantedAuthority中
            List<Role> roles = roleService.getRolesByAccountId( account.getId() );
            for (Role role : roles) {
                if (role != null && role.getCode()!=null) {
                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getCode());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }

            User act = new User( account.getId(), account.getUsername(), account.getPassword(), orgId, staffId, departmentId, grantedAuthorities );
            if (StringUtils.isNotEmpty( account.getOrgId() )) {
                //管理机构
                act.setOrgId( account.getOrgId() );
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

            act.setRole( roles );
            act.setStaffName( staffName );
            act.setRoleName( onlineName );
            return act;
        } else {
            logger.info( "用户" + username + " 不存在" );
            throw new UsernameNotFoundException( "用户名或密码不正确" );
        }
    }
}

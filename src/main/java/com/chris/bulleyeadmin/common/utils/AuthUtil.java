package com.chris.bulleyeadmin.common.utils;

import com.chris.bulleyeadmin.common.entity.Constants;
import com.chris.bulleyeadmin.system.pojo.Account;
import com.chris.bulleyeadmin.system.pojo.Role;
import com.chris.bulleyeadmin.system.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Component
public class AuthUtil {

//    public static final ThreadLocal<Account> local = new ThreadLocal<>();

    @Autowired
    private HttpServletRequest request;

    public static User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            Object object = authentication.getPrincipal();
            User user = null;
            if (object instanceof User) {
                user = (User) object;
                return user;
            }
        }
        return null;
    }

    public static User getCurrentUser(HttpServletRequest request) {
        if (request == null) {
            return null;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = getCurrentUser();
            session.setAttribute("user", user);
        }
        return user;
    }

    public static void changeRole(String id, String name) {
        User user = getCurrentUser();
        for (Role role : AuthUtil.getCurrentUser().getRole()) {
            if (role.getId().equals(id)) {
                user.setCurrentRole(role);
            }
        }
    }

    /**
     * 获取用户的数据浏览权限
     *
     * @return
     */
    public static String getAuthFlag() {
        StringBuilder sb = new StringBuilder();
        Role role = getCurrentUser().getCurrentRole();
       /* if (act != null) {
            for (Role role : act.getRole()) {
                sb.append(role.getDataAuthFlag());
                sb.append(";");
            }
        }*/

        String strAuths = role.getDataAuthFlag();
        if (strAuths.contains(Constants.ORGANIZATION)) {
            return Constants.ORGANIZATION; //全院
        }
        if (strAuths.contains(Constants.DEPARTMENT)) {
            return Constants.DEPARTMENT; //当前科室
        }

        return Constants.PERSONAL;  //当前用户
    }

}

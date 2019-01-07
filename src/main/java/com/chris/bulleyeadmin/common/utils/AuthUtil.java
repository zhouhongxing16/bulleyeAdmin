package com.chris.bulleyeadmin.common.utils;

import com.chris.bulleyeadmin.common.pojo.Constants;
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
import java.util.Map;

@Component
public class AuthUtil {

//    public static final ThreadLocal<Account> local = new ThreadLocal<>();

    @Autowired
    private HttpServletRequest request;

    private static User getCurrentSpringUser() {
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

    public static User getCurrentUser() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return getCurrentUser(request);
    }

    public static User getCurrentUser(HttpServletRequest request) {
        if(request == null) {
            return null;
        }

        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null) {
            user = getCurrentSpringUser();
            session.setAttribute("user",user);
        }
        return user;
    }

    /**
     * 获取用户的数据浏览权限
     *
     * @return
     */
    public static String getAuthFlag() {
        StringBuilder sb = new StringBuilder();
        User act = getCurrentUser();
        if (act != null) {
            for (Role role : act.getRole()) {
                sb.append( role.getDataAuthFlag() );
                sb.append( ";" );
            }
        }

        String strAuths = sb.toString();
        if (strAuths.contains(Constants.ORG )) {
            return Constants.ORG; //全院
        }
        if (strAuths.contains( Constants.DEPARTMENT )) {
            return Constants.DEPARTMENT; //当前科室
        }
        if (strAuths.contains( Constants.ORGADMIN )) {
            return Constants.ORGADMIN;  //行政管理机构（卫计委）
        }

        return Constants.PERSONAL;  //当前用户
    }

    public Map<String, Object> getSpeical() {
        return (Map<String, Object>) request.getSession().getAttribute( "userInfo" );
    }

}

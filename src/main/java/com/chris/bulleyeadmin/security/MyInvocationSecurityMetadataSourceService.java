package com.chris.bulleyeadmin.security;

import com.chris.bulleyeadmin.mapper.RoleMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Component
public class MyInvocationSecurityMetadataSourceService implements FilterInvocationSecurityMetadataSource {

    @Autowired
    RoleMenuMapper roleMenuMapper;

    private HashMap<String, Collection<ConfigAttribute>> hashMap = null;

    /**
     * 加载权限表中所有权限，这里不想从数据库中获取直接写在了这
     */
    public void loadResourceDefine() {
        hashMap = new HashMap<>();
        ConfigAttribute cfg;
        System.out.println("===============================");
        System.out.println("初始化加载");
        System.out.println("===============================");
        List<Map<String,String>> roleMap = roleMenuMapper.getRoleAuthPaths();
        Collection<ConfigAttribute> array;
        for (Map<String,String> map : roleMap) {
            array = new ArrayList<>();
            if(hashMap.get(map.get("path"))==null){
                cfg = new SecurityConfig(map.get("code"));
                array.add(cfg);
                hashMap.put(map.get("path"),array);
            }else{
                cfg = new SecurityConfig(map.get("code"));
                array.add(cfg);
                hashMap.put(map.get("path"),array);
            }
        }
        System.out.println(hashMap.toString());
    }
    //此方法是为了判定用户请求的url 是否在权限表中，如果在权限表中，则返回给 decide 方法，用来判定用户是否有此权限。如果不在权限表中则放行。
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        if(hashMap ==null) loadResourceDefine();
        //object 中包含用户请求的request 信息
        HttpServletRequest request = ((FilterInvocation) object).getHttpRequest();
        AntPathRequestMatcher matcher;
        String resUrl;
        for(Iterator<String> iter = hashMap.keySet().iterator(); iter.hasNext(); ) {
            resUrl = iter.next();
            //matches() 方法用于检测字符串是否匹配给定的正则表达式
            matcher = new AntPathRequestMatcher(resUrl);
            //首先判断请求方法是否匹配，如果匹配继续判断是否对该用户授权
            if (matcher.matches(request)) {
                return hashMap.get(resUrl);
            }
        }
        return null;
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        //UsernamePasswordAuthenticationToken.class.equals(clazz);
        //return FilterInvocation.class.isAssignableFrom(aClass);
        return true;
    }
}

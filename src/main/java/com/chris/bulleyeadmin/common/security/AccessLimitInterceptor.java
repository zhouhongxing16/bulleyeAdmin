package com.chris.bulleyeadmin.common.security;

import com.chris.bulleyeadmin.common.config.AccessLimit;
import com.chris.bulleyeadmin.common.utils.IPUtils;
import com.chris.bulleyeadmin.common.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Chris
 * @date 2022-01-06 14:46
 */
@Component
public class AccessLimitInterceptor implements HandlerInterceptor {

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Method method = handlerMethod.getMethod();
            if (!method.isAnnotationPresent(AccessLimit.class)) {
                return true;
            }
            AccessLimit accessLimit = method.getAnnotation(AccessLimit.class);
            if (accessLimit == null) {
                return true;
            }
            int limit = accessLimit.limit();
            int sec = accessLimit.sec();
            String key = IPUtils.getIpAddr(request) + request.getRequestURI();
            String v  =redisUtil.get(key);
            Integer maxLimit = v ==null?null:Integer.valueOf(v);
            if (maxLimit == null) {
                redisUtil.set(key, "1", sec, TimeUnit.SECONDS);  //set时一定要加过期时间
            } else if (maxLimit < limit) {

                redisUtil.set(key, (maxLimit + 1)+"", sec, TimeUnit.SECONDS);
            } else {
                output(response, "请求太频繁!");
                return false;
            }
        }
        return true;
    }
    public void output(HttpServletResponse response, String msg) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            outputStream.write(msg.getBytes("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            outputStream.flush();
            outputStream.close();
        }
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
    }
}
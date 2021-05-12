package com.chris.bulleyeadmin.common.utils;

import com.alibaba.fastjson.JSON;
import com.chris.bulleyeadmin.system.pojo.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * chris
 * 2021-05-12
 */
@Component
public class LoginUserUtil {

    private static String LOGIN_USER_TOKEN_PREFIX = "login:user-token:";

    @Autowired
    private RedisUtil redisUtil;

    /***
     * 用户token续期超时时间,单位:分钟
     */
    private static long LOGIN_OUT_TIME_MINUTE = 15L;

    /***
     * redis 用户信息过期时间  2小时
     */
    private static long REDIS_OUT_TIME = 60 * 2;

    /***
     * 通过token获取当前用户
     * @param token
     * @return
     */
    public User getUserInfo(String token) {
        final String key = LOGIN_USER_TOKEN_PREFIX + token;
        String userStr = redisUtil.get(key);
        if (StringUtils.isEmpty(userStr)) {
            return null;
        }
        User loginUser = JSON.parseObject(userStr, User.class);
        // 验证登录失效时间(距离失效时间小于15分钟时,重新设置用户redis信息)
        if (loginUser.getExpireTime() != null &&
                DateUtils.getDurationMinute(LocalDateTime.now(),loginUser.getExpireTime()) <= LOGIN_OUT_TIME_MINUTE) {
            // 更新Redis中保存的用户信息(token有效期增加2小时)
            this.setUserInfo(token, loginUser);
        }
        return loginUser;
    }

    /***
     * 设置redis里面的用户信息,以及过期时间
     * @param token
     * @param loginUser
     */
    public void setUserInfo(String token, User loginUser){
        //登陆失效时间
        LocalDateTime expireTime = loginUser.getExpireTime() != null ? loginUser.getExpireTime() : LocalDateTime.now();
        loginUser.setExpireTime(expireTime.plusHours(2));
        if(StringUtils.isEmpty(loginUser.getToken())){
            loginUser.setToken(token);
        }
        redisUtil.setEx(LOGIN_USER_TOKEN_PREFIX + token,JSON.toJSONString(loginUser),REDIS_OUT_TIME,TimeUnit.MINUTES);
    }

    /***
     * 移除用户信息
     * @param token
     */
    public void removeUserInfo(String token){
        redisUtil.delete(LOGIN_USER_TOKEN_PREFIX + token);
    }

}

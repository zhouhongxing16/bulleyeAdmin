package com.chris.bulleyeadmin.common.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author sunwei
 * @description 分布式锁
 * @date 2019/1/17 15:29
 */
@Slf4j
@Component("distributedLock")
public class DistributedLock {
    private static final Map<String, String> LOCAL_MAP = new HashMap<String,String>();

    private StringRedisTemplate redis;

    private final String LOCK_VALUE = "locked";

    public DistributedLock(StringRedisTemplate redisTemplate){
        this.redis = redisTemplate;
    }

    private boolean lock(String key, int expireMinutes) {
        Boolean setnx = redis.boundValueOps(LOCK_VALUE + key).setIfAbsent(LOCK_VALUE);
        if (setnx) {
            redis.expire(LOCK_VALUE + key, expireMinutes, TimeUnit.MINUTES);
        }
        return setnx;
    }

    private void mustLock(String key, int expireMinutes) {
        while (true) {
            Boolean setnx = redis.boundValueOps(LOCK_VALUE + key).setIfAbsent(LOCK_VALUE);
            if (setnx) {
                redis.expire(LOCK_VALUE + key, expireMinutes, TimeUnit.MINUTES);
                break;
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error(e.getMessage());
            }
        }
    }

    private void unlock(String key) {
        redis.delete(LOCK_VALUE + key);
    }

    /***
     * 分布式锁只执行一次任务
     * @param key
     * @param expireMinutes
     * @param callBack
     * @param <T>
     * @return
     */
    public <T> T doActionWithCancel(String key, int expireMinutes, CallBack<T> callBack) {
        String lockKey = getLockKey(key);

        synchronized (lockKey) {
            try {
                if (!lock(lockKey, expireMinutes)) {
                    return null;
                }
                log.debug("分布式任务:{} runnin..", lockKey);
                T t = callBack.doAction();
                log.debug("分布式任务:{} finish..", lockKey);
                //每次执行完后,休眠10s,保证多台服务器不重复执行(主要排除时差问题)
                try {
                    Thread.sleep(10000L);
                } catch (Exception e) {
                    log.error("分布式任务,执行完成后,休眠10s异常!!!!!");
                }
                return t;
            } finally {
                //unlock(lockKey);
            }
        }
    }

    private String getLockKey(String key) {
        String lockKey = LOCAL_MAP.get(key);
        if (StringUtils.isEmpty(lockKey)) {
            synchronized (LOCAL_MAP) {
                lockKey = LOCAL_MAP.get(key);
                if (StringUtils.isEmpty(lockKey)) {
                    LOCAL_MAP.put(key, key);
                    lockKey = key;
                }
            }
        }
        return lockKey;
    }

    public <T> T doAction(String key, int expireMinutes, CallBack<T> callBack) {
        String lockKey = getLockKey(key);
        synchronized (lockKey) {
            try {
                mustLock(lockKey, expireMinutes);
                log.debug("分布式任务:{} runnin..", lockKey);
                T t = callBack.doAction();
                log.debug("分布式任务:{} finish..", lockKey);
                return t;
            } finally {
                unlock(lockKey);
            }
        }
    }

    /***
     * 回调方法
     * @param <T>
     */
    @FunctionalInterface
    public interface CallBack<T> {
        T doAction();
    }
}


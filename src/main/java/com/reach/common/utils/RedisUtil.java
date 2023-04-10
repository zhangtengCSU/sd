package com.reach.common.utils;


import cn.hutool.extra.spring.SpringUtil;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class RedisUtil {
    private volatile static RedisTemplate redisTemplate;

    private RedisUtil() {
        // no instance
    }

    /**
     * fetch RedisTemplate instance
     */
    private static RedisTemplate fetchRedisTemplate() {
        if (Objects.isNull(redisTemplate)) {
            synchronized (RedisUtil.class) {
                if (Objects.isNull(redisTemplate)) {
                    redisTemplate = SpringUtil.getBean(RedisTemplate.class);
                }
            }
        }
        return redisTemplate;
    }

    /**
     * set string
     */
    public static boolean setString(String key, String value) {
        fetchRedisTemplate().opsForValue().set(key, value, 1800L, TimeUnit.SECONDS);
        return true;
    }

    /**
     * set string
     */
    public static boolean setString(String key, String value, long seconds) {
        fetchRedisTemplate().opsForValue().set(key, value, seconds, TimeUnit.SECONDS);
        return true;
    }

    /**
     * set object
     */
    public static boolean setObject(String key, Object object) {
        fetchRedisTemplate().opsForValue().set(key, JsonUtil.objectToJson(object), 1800l, TimeUnit.SECONDS);
        return true;
    }

    /**
     * set object
     */
    public static boolean setObject(String key, Object object, long seconds) {
        fetchRedisTemplate().opsForValue().set(key, JsonUtil.objectToJson(object), seconds, TimeUnit.SECONDS);
        return true;
    }

    /**
     * get string
     */
    public static String getString(String key) {
        Object o = fetchRedisTemplate().opsForValue().get(key);
        if (Objects.isNull(o)) return null;
        return o.toString();
    }

    /**
     * get object
     */
    public static <T> T getObject(String key, Class<T> clazz) {
        Object object = fetchRedisTemplate().opsForValue().get(key);
        if (Objects.isNull(object)) return null;
        return JsonUtil.jsonToObject(object.toString(), clazz);
    }


    /**
     * delete key
     */
    public static boolean deleteKey(String key) {
        return fetchRedisTemplate().delete(key);
    }


}

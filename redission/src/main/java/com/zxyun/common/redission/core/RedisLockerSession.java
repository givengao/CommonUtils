package com.zxyun.common.redission.core;

import org.redisson.api.RLock;

import java.util.concurrent.TimeUnit;

/**
 * @des:
 * @Author: given
 * @Date 2020/4/28 11:45
 */
public interface RedisLockerSession {

    /**
     * 加锁
     * @param key
     */
    RLock lock (String key);

    /**
     * 释放锁
     * @param key
     */
    void unLock (String key);

    /**
     * 加锁，有等待时间限制
     * @param key
     * @param timeUnit
     * @param lessTime
     */
    void lock (String key, TimeUnit timeUnit, long lessTime);
}

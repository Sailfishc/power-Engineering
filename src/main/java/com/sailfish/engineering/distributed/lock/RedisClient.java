package com.sailfish.engineering.distributed.lock;

/**
 * Redis分布式锁
 *
 * @author sailfish
 * @create 2019-10-18-09:54
 */
public class RedisClient {

    public boolean lock(String key, String requestId) {

        return false;
    }


    public boolean lock(String key, String requestId, int expireSecond) {

        return false;
    }


    public boolean unLock(String key, String requestId) {

        return false;
    }

    public boolean tryLock(String key, String requestId, int expireSecond) {

        return false;
    }
}

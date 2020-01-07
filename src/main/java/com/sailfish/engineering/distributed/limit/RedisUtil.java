package com.sailfish.engineering.distributed.limit;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @author sailfish
 * @create 2019-10-18-11:47
 */
public class RedisUtil {

//    private static final String HOST = "redis.bass.3g";
    private static final String HOST = "58.87.97.129";

    private static final String OK = "OK";

    private static final String UNLOCK_OK = "1";

    private static Jedis jedis = null;


    /**
     * 获取Jedis
     *
     * @return
     */
    public static Jedis getRedisClient() {
        if (jedis == null) {
            JedisPool pool = new JedisPool(HOST);
            return pool.getResource();
        }
        return jedis;
    }
}

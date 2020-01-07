package com.sailfish.engineering.distributed.redis;

import com.sailfish.engineering.distributed.limit.RedisUtil;

import org.junit.Test;

import java.util.stream.IntStream;

import redis.clients.jedis.Jedis;

import static org.junit.Assert.*;

/**
 * @author sailfish
 * @create 2019-10-18-16:59
 */
public class RedisCommandTest {


    @Test
    public void test_scan_command() {
        final Jedis client = RedisUtil.getRedisClient();
        IntStream.range(1, 10000).forEach(i -> {
                    client.setex("key" + i, 600, String.valueOf(i));
                }
        );
    }
}
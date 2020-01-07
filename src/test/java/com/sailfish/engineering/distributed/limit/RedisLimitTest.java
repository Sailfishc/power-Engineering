package com.sailfish.engineering.distributed.limit;

import org.junit.Test;

/**
 * @author sailfish
 * @create 2019-10-18-11:53
 */
public class RedisLimitTest {

    @Test
    public void limit() {

        Limit limit = new RedisLimit();
        for (int i = 0; i < 20; i++) {
            System.out.println(limit.singleLimit("name", "reply", 60, 5));
        }
    }

    @Test
    public void funnelLimit() {
        Limit limit = new RedisLimit();

        for (int i = 0; i < 20; i++) {
            System.out.println(i + "--" + limit.funnelLimit("name1", "reply", 15, 0.5));
        }
    }
}
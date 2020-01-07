package com.sailfish.engineering.distributed.limit;

import com.google.common.collect.Maps;

import java.util.Map;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;

/**
 * 不适合做限定时间内限定大量次数（百万）这种场景，会消耗大量存储
 * @author sailfish
 * @create 2019-10-18-11:45
 */
public class RedisLimit implements Limit{


    @Override
    public boolean singleLimit(String userId, String requestId, int period, int maxNum) {
        String key = String.format("hist:%s:%s", userId, requestId);
        final Jedis jedis = RedisUtil.getRedisClient();
        final long nowTs = System.currentTimeMillis();
        final Pipeline pipe = jedis.pipelined();
        pipe.multi();
        pipe.zadd(key, nowTs, "" + nowTs);
        // 移除时间窗口之前的行为记录，剩下的都是时间窗口内的
        pipe.zremrangeByScore(key, 0, nowTs - period * 1000);
        final Response<Long> count = pipe.zcard(key);
        //  设置zset的过期时间，避免冷用户持续占用内存
        pipe.expire(key, period + 1);
        pipe.exec();
        pipe.close();
        return count.get() <= maxNum;
    }

    private Map<String, Funnel> funnels = Maps.newHashMap();

    @Override
    public boolean funnelLimit(String userId, String requestId, int capacity, double leakingRate) {
        String key = String.format("hist:%s:%s", userId, requestId);
        Funnel funnel = funnels.get(key);
        if (funnel == null) {
            funnel = new Funnel(capacity, leakingRate);
            funnels.put(key, funnel);
        }
        return funnel.watering(1);
    }

}

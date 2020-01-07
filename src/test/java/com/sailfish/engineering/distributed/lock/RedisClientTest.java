package com.sailfish.engineering.distributed.lock;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Collections;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

/**
 * @author sailfish
 * @create 2019-10-18-09:55
 */
public class RedisClientTest {

    private static final String HOST = "redis.bass.3g";

    private static final String OK = "OK";

    private static final String UNLOCK_OK = "1";


    static Jedis jedis = null;

    @BeforeClass
    public static void setUp() throws Exception {
        JedisPool pool = new JedisPool(HOST);
        jedis = pool.getResource();
    }

    @Test
    public void test_jedis() {
        jedis.set("name", "sailfish");
        System.out.println(jedis.get("name"));
    }


    @Test
    public void test_jedis_setNX() {
        SetParams params = new SetParams();
        params.nx();
        params.ex(3);

        final String set = jedis.set("name", "sailfish", params);
        System.out.println(set);

        final String result = jedis.set("key", "sailfish", params);
        System.out.println(result);
    }


    /**
     * 方式一个线程业务执行需要3s，但是获得锁的时间是2s，导致第二个线程获取锁后由第二个线程去释放锁
     */
    @Test
    public void test_lua_script() {
        final String key = "lock1";
//        lua script
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";

        SetParams params = new SetParams();
        params.nx();
        params.ex(3);

        final String set = jedis.set(key, "sailfish", params);
        Object result = null;
        if (OK.equalsIgnoreCase(set)) {
            // 如果加锁的Key和传入的参数一致的话就del-key，否则不删除（解锁）
            result = jedis.eval(script, Collections.singletonList(key), Collections.singletonList("b"));
            System.out.println(result);
        }

        if (UNLOCK_OK.equals(result)) {
            System.out.println("true");
        } else {
            System.out.println("false");
        }
    }
}
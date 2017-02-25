package me.thomas.knowledge.cache.redis;

import redis.clients.jedis.Jedis;

/**
 * Created by zhaoxs on 2016/1/26 0026.
 */
public class TenMinuteTutorial {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1", 6379);
        jedis.set("name", "thomas");
        jedis.append("name", " hello");
        System.out.println(jedis.get("name"));

        // 使用自增操作时，可以先不用先设置为0，因为当这个变量不存在时，会自动设置为0。
//        jedis.set("counter", "0");
        jedis.incr("counter");
        System.out.println(jedis.get("counter"));
    }
}

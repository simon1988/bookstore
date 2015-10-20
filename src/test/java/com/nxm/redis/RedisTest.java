package com.nxm.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

@RunWith(JUnit4.class)
public class RedisTest {
	@Test
	public void testJedis(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);
		poolConfig.setMinIdle(10);
		JedisPool pool = new JedisPool(poolConfig, "127.0.0.1", 6379, 2000, "simon1988", 0);
		try (Jedis jedis = pool.getResource()) {
			  jedis.set("foo", "bar");
			  String foobar = jedis.get("foo");
			  System.out.println(foobar);
			  jedis.hset("map", "key", "");
			  System.out.println(jedis.hget("map", "key"));
		}
		pool.close();
	}
}

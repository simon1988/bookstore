package com.nxm.redis;

import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.Pipeline;
import redis.clients.jedis.Response;
import redis.clients.jedis.Transaction;

@RunWith(JUnit4.class)
public class RedisTest {
	private static JedisPool pool;
	@BeforeClass
	public static void init(){
		JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxIdle(30);
		poolConfig.setMinIdle(10);
		pool = new JedisPool(poolConfig, "127.0.0.1", 6379, 2000, "simon1988", 0);
	}
	@Test
	public void basic(){
		try (Jedis jedis = pool.getResource()) {
			  jedis.set("foo", "bar");
			  String foobar = jedis.get("foo");
			  System.out.println(foobar);
			  jedis.hset("map", "key", "");
			  System.out.println(jedis.hget("map", "key"));
		}
	}
	@Test
	public void transaction(){
		try (Jedis jedis = pool.getResource()) {
			  jedis.watch("val");
			  int val = Integer.parseInt(jedis.get("val"));
			  Transaction t = jedis.multi();
			  t.set("val", ""+(val+1));
			  List<Object> res = t.exec();
			  System.out.println(res);
			  System.out.println(jedis.get("val"));
		}
	}
	@Test
	public void pipeline(){
		try (Jedis jedis = pool.getResource()) {
			Pipeline pipe = jedis.pipelined();
			pipe.incr("val");
			pipe.incr("val");
			Response<String> val = pipe.get("val");
			pipe.sync();
			System.out.println(val.get());
		}
	}
	@AfterClass
	public static void destroy(){
		pool.close();
	}
}

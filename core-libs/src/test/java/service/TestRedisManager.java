package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.GenericToStringSerializer;

import base.SpringContextTestCase;
import redis.clients.jedis.JedisPoolConfig;

public class TestRedisManager extends SpringContextTestCase {
	@Autowired
	private RedisManager redisManager;

	@Test
	public void testAddStringData() {
		String key = "key";
		String value = "value";

		redisManager.addStringData(key, value);
	}

	@Test
	public void testRedis() {
		// 连接本地的 Redis 服务
		//Jedis jedis = new Jedis("127.0.0.1");
		//System.out.println("Connection to server sucessfully");
		// 查看服务是否运行
		//System.out.println("Server is running: " + jedis.ping());
		
		JedisPoolConfig pool = new JedisPoolConfig();
		pool.setMaxIdle(100);
		pool.setMaxWaitMillis(1000);
		pool.setTestOnBorrow(true);
		
		JedisConnectionFactory jedisFactory = new JedisConnectionFactory(pool);
		jedisFactory.setHostName("127.0.0.1");
		jedisFactory.setPort(6379);
		jedisFactory.setPassword("");
		
		GenericToStringSerializer genericToStringSerializer = new GenericToStringSerializer<>(Long.class);
		
		RedisTemplate redisTemplate = new RedisTemplate<String,String>();
		redisTemplate.setConnectionFactory(jedisFactory);
		redisTemplate.setDefaultSerializer(genericToStringSerializer);
		
		(redisTemplate.opsForValue()).set("key", "value");
	}
}

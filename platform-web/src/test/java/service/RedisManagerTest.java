package service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import support.SpringContextTestCase;

public class RedisManagerTest extends SpringContextTestCase{
	@Autowired
	private RedisManager redisManager;

	@Test
	public void testAddStringData() {
		String key = "key";
		String value = "value";

		redisManager.addStringData(key, value);
	}
}

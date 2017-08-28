package service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

/**
 * redis服务类
 * 
 * @author xzeng
 *
 */
//@Service
public class RedisManager {
	@Autowired
	private RedisTemplate<String, String> redisOps;
	@Resource(name = "redisTemplate")
	private ValueOperations<String, String> valueOps;
	@Resource(name = "redisTemplate")
	private ListOperations<String, String> listOps;
	@Resource(name = "redisTemplate")
	private SetOperations<String, String> setOps;
	@Resource(name = "redisTemplate")
	private HashOperations<String, String, String> hashOps;
	@Resource(name = "redisTemplate")
	private ZSetOperations<String, String> zSetOps;

	private final String LOCK_KEY_PREFIX = "lock:";
	private final int LOCK_RETRY_TIMES = 5;

	public String increment(String key) {
		return increment(key, 1);
	}

	public String increment(String key, long value) {
		return valueOps.increment(key, value).toString();
	}

	// zset score加值
	public double increment(String zsetKey, long score, String value) {
		return zSetOps.incrementScore(zsetKey, value, score);
	}

	// hash value 加值
	public long increment(String hashKey, String key, long count) {
		return hashOps.increment(hashKey, key, count);
	}

	public void addStringData(String strKey, String value) {
		valueOps.set(strKey, value);
	}

	public long addSetData(String setKey, String value) {
		return setOps.add(setKey, value);
	}

	public void addHashData(String hashKey, Map<String, String> data) {
		hashOps.putAll(hashKey, data);
	}

	public void addZsetData(String zsetKey, double score, String value) {
		zSetOps.add(zsetKey, value, score);
	}

	// 返回zset中的score
	public double getZsetScore(String zsetKey, String value) {
		return zSetOps.score(zsetKey, value);
	}

	// 获取反序部分值
	public Set<String> getZsetRevrange(String zsetKey, int start, int end) {
		return zSetOps.reverseRange(zsetKey, start, end);
	}

	// 获取所有hash数据
	public Map<String, String> getHashDataAll(String hashKey) {
		return hashOps.entries(hashKey);
	}

	public void expire(String key, int seconds) {
		redisOps.expire(key, seconds, TimeUnit.SECONDS);
	}

	/**
	 * 获取锁并设置过期时间
	 * 
	 * @param lockName
	 * @param lockTimeout
	 * @return
	 */
	public String acquireLockWithTimeout(String lockName, long lockTimeout) {
		String identifier = UUID.randomUUID().toString();
		String lockKey = LOCK_KEY_PREFIX + lockName;

		int retryTimes = 0;
		while (retryTimes < LOCK_RETRY_TIMES) {
			// 重试次数+1
			retryTimes++;
			// 获取锁操作
			if (valueOps.setIfAbsent(lockKey, identifier)) {
				// 设置过期时间
				redisOps.expire(lockKey, lockTimeout, TimeUnit.SECONDS);
				return identifier;
			}
			// 判断该锁是否有过期时间
			if (redisOps.getExpire(lockKey) == -1) {
				// 设置过期时间
				redisOps.expire(lockKey, lockTimeout, TimeUnit.SECONDS);
			}
			// 休眠段时间重试
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		// aquire lock fail.
		return null;
	}

	/**
	 * 释放锁
	 * 
	 * @param lockName
	 * @param identifier
	 * @return
	 */
	public boolean releaseLock(String lockName, String identifier) {
		String lockKey = LOCK_KEY_PREFIX + lockName;
		while (true) {
			// 加上乐观锁
			redisOps.watch(lockKey);
			if (identifier.equals(valueOps.get(lockKey))) {
				// 事务开启
				redisOps.multi();
				redisOps.delete(lockKey);
				// 事务提交
				List<Object> result = redisOps.exec();
				if (result == null) {
					continue;
				}
				return true;
			}
			// 释放锁
			redisOps.unwatch();
			break;
		}
		return false;
	}

	public ValueOperations<String, String> getValueOps() {
		return valueOps;
	}

	public void setValueOps(ValueOperations<String, String> valueOps) {
		this.valueOps = valueOps;
	}

	public ListOperations<String, String> getListOps() {
		return listOps;
	}

	public void setListOps(ListOperations<String, String> listOps) {
		this.listOps = listOps;
	}

	public SetOperations<String, String> getSetOps() {
		return setOps;
	}

	public void setSetOps(SetOperations<String, String> setOps) {
		this.setOps = setOps;
	}

	public HashOperations<String, String, String> getHashOps() {
		return hashOps;
	}

	public void setHashOps(HashOperations<String, String, String> hashOps) {
		this.hashOps = hashOps;
	}

	public ZSetOperations<String, String> getzSetOps() {
		return zSetOps;
	}

	public void setzSetOps(ZSetOperations<String, String> zSetOps) {
		this.zSetOps = zSetOps;
	}

	public RedisTemplate<String, String> getRedisOps() {
		return redisOps;
	}

	public void setRedisOps(RedisTemplate<String, String> redisOps) {
		this.redisOps = redisOps;
	}

}

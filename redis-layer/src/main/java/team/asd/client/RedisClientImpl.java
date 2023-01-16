package team.asd.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.List;
import java.util.Map;

@Repository
public class RedisClientImpl implements RedisClient {

	private final Long HASH_EXPIRE = 21600L;
	private final Jedis jedis;

	public RedisClientImpl(@Autowired JedisPool jedisPool) {
		this.jedis = jedisPool.getResource();
	}

	@Override
	public String readByKey(String key) {
		return jedis.get(key);
	}

	@Override
	public String saveValueByKey(String key, String value) {
		return jedis.set(key, value);
	}

	@Override
	public Long saveList(String keyList, List<String> list) {
		list.forEach(value -> jedis.rpush(keyList, value));
		return (long) list.size();
	}

	@Override
	public Long saveElementIntoList(String keyList, String value) {
		return jedis.rpush(keyList, value);
	}

	@Override
	public List<String> retrieveList(String keyList) {
		return jedis.lrange(keyList, 0, -1);
	}

	@Override
	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		return jedis.hset(primaryKey, secondaryKey, value);
	}

	@Override
	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		return jedis.hget(primaryKey, secondaryKey);
	}

	@Override
	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		return jedis.hgetAll(primaryKey);
	}

	@Override
	public String saveValueWithExpireDate(String key, String value, Long expireDate) {
		return jedis.setex(key, expireDate, value);
	}

	@Override
	public void saveProductMigrationWithExpire(Integer productId, String fromDate, String toDate, double result) {
		jedis.hset("migration_result:" + productId, fromDate + ":" + toDate, String.valueOf(result));
		jedis.expire("migration_result:" + productId, HASH_EXPIRE);
	}

	@Override
	public String readProductMigrationResult(Integer productId, String fromDate, String toDate) {
		return jedis.hget("migration_result:" + productId, fromDate + ":" + toDate);
	}
}

package team.asd.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import redis.clients.jedis.JedisPooled;

import java.util.List;
import java.util.Map;

@Repository
public class RedisClientImpl implements RedisClient {

	private final JedisPooled jedisPool;

	public RedisClientImpl(@Autowired JedisPooled jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String readByKey(String key) {
		return jedisPool.get(key);
	}

	@Override
	public String saveValueByKey(String key, String value) {
		return jedisPool.set(key, value);
	}

	@Override
	public Long saveList(String keyList, List<String> list) {
		list.forEach(value -> jedisPool.rpush(keyList, value));
		return (long) list.size();
	}

	@Override
	public Long saveElementIntoList(String keyList, String value) {
		return jedisPool.rpush(keyList, value);
	}

	@Override
	public List<String> retrieveList(String keyList) {
		return jedisPool.lrange(keyList, 0, -1);
	}

	@Override
	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		return jedisPool.hset(primaryKey, secondaryKey, value);
	}

	@Override
	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		return jedisPool.hget(primaryKey, secondaryKey);
	}

	@Override
	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		return jedisPool.hgetAll(primaryKey);
	}

	@Override
	public String saveValueWithExpireDate(String key, String value, long expireDate) {
		return jedisPool.setex(key, expireDate, value);
	}
}

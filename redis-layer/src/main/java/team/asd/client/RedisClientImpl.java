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
		return null;
	}

	@Override
	public Long saveList(String keyList, List<String> list) {
		return null;
	}

	@Override
	public Long saveElementIntoList(String keyList, String value) {
		return null;
	}

	@Override
	public List<String> retrieveList(String keyList) {
		return null;
	}

	@Override
	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		return null;
	}

	@Override
	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		return null;
	}

	@Override
	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		return null;
	}

	@Override
	public String saveValueWithExpireDate(String key, String value, long expireDate) {
		return null;
	}
}

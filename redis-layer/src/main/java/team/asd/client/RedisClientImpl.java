package team.asd.client;

import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPooled;

public class RedisClientImpl implements RedisClient {

	private final JedisPooled jedisPool;

	public RedisClientImpl(@Autowired JedisPooled jedisPool) {
		this.jedisPool = jedisPool;
	}

	@Override
	public String readByKey(String key) {
		return jedisPool.get(key);
	}
}

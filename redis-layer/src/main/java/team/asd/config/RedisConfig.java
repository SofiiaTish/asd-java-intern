package team.asd.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Configuration
@ComponentScan({"team.asd"})
public class RedisConfig {

	@Bean
	@Primary
	public RedisProperties redisProperties() {
		return new RedisProperties();
	}

	@Bean
	public JedisPool jedisPool() {
		RedisProperties properties = redisProperties();
		GenericObjectPoolConfig<Jedis> poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxIdle(0);
		return new JedisPool(poolConfig, properties.getHost(), properties.getPort(), 2000, properties.getPassword());
	}

}

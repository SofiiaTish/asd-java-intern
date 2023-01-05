package team.asd.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import redis.clients.jedis.Connection;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPooled;

@Configuration
@ComponentScan({"team.asd"})
public class RedisConfig {

	@Bean
	@Primary
	public RedisProperties redisProperties() {
		return new RedisProperties();
	}

	@Bean
	public JedisPooled jedisPool() {
		RedisProperties properties = redisProperties();
		GenericObjectPoolConfig<Connection> poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxIdle(0);
		return new JedisPooled(poolConfig, properties.getHost(), properties.getPort());
	}

	/*@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
		JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
		GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		poolConfig.setMaxIdle(0);
		JedisClientConfiguration jedisClientConfiguration = builder.usePooling().poolConfig(poolConfig).build();

		RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
		RedisProperties properties = redisProperties();
		redisConfiguration.setDatabase(properties.getDatabase());
		redisConfiguration.setHostName(properties.getHost());
		redisConfiguration.setPort(properties.getPort());

		return new JedisConnectionFactory(redisConfiguration, jedisClientConfiguration);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}*/

}

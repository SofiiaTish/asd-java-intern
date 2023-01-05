package team.asd.config;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration.JedisClientConfigurationBuilder;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import redis.clients.jedis.JedisPoolConfig;

@Configuration
@ComponentScan({"team.asd"})
public class RedisConfig {

	@Bean
	@ConfigurationProperties(prefix = "spring.redis")
	JedisConnectionFactory jedisConnectionFactory() {
		/*JedisClientConfigurationBuilder builder = JedisClientConfiguration.builder();
		GenericObjectPoolConfig<Object> poolConfig = new GenericObjectPoolConfig<>();
		poolConfig.setMaxTotal(10);
		poolConfig.setMaxIdle(10);
		JedisClientConfiguration jedisClientConfiguration = builder.usePooling().poolConfig(poolConfig).build();*/
		RedisSentinelConfiguration redisConfiguration = new RedisSentinelConfiguration();
		JedisPoolConfig jedisPoolConfig = buildPoolConfig();
		return new JedisConnectionFactory(redisConfiguration, jedisPoolConfig);
	}

	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(jedisConnectionFactory());
		return template;
	}

	private JedisPoolConfig buildPoolConfig() {
		final JedisPoolConfig poolConfig = new JedisPoolConfig();
		poolConfig.setMaxTotal(10);
		poolConfig.setMinIdle(0);
		poolConfig.setMaxIdle(10);
		return poolConfig;
	}
}

package team.asd.client;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import redis.clients.jedis.JedisPooled;
import team.asd.config.RedisConfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedisClientTest {

	private AnnotationConfigApplicationContext context;
	private RedisClientImpl client;

	@BeforeEach
	public void setUp() {
		context = new AnnotationConfigApplicationContext(RedisConfig.class);
		client = context.getBean("redisClientImpl", RedisClientImpl.class);
	}

	@AfterEach
	public void tearDown() {
		context.close();
	}

	@Test
	void readByKey() {
		assertEquals("test-value", client.readByKey("test-key"));
	}

}
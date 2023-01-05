package team.asd.client;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class RedisClientTest {

	@Autowired
	private RedisClientImpl client;

	@Test
	void readByKey() {
	 	assertEquals("test-value",client.readByKey("test-key"));
	}
}
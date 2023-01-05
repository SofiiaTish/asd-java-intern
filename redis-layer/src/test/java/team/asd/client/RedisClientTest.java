package team.asd.client;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RedisClientTest {

	@Autowired
	private RedisClientImpl client;

	@Test
	void readByKey() {
		assertEquals("test-value", client.readByKey("test-key"));
	}

	@Test
	void tester() {
		List<String> list = new ArrayList<>(Arrays.asList("hello", "get", "go"));
		Object o = String.valueOf(list);
		System.out.println(o.getClass());
	}
}
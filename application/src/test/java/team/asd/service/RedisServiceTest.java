package team.asd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class RedisServiceTest {

	@Autowired
	private RedisService redisService;

	@BeforeEach
	void setUp() {
	}

	@AfterEach
	void tearDown() {
	}

	@Test
	void testSaveReadValueByKey() {
		assertNotNull(redisService.saveValueByKey("first-key", "first-value"));

		assertEquals("first-value", redisService.readByKey("first-key"));

		assertNotNull(redisService.saveValueByKey("second-key", null));
		String result = redisService.readByKey("second-key");
		assertNotNull(result);
		assertEquals("null", result);
	}

	@Test
	void testSaveIntoList() {
		assertNotNull(redisService.saveList("list-key", Arrays.asList("first", "second")));
		assertNotNull(redisService.saveElementIntoList("list-key", "third"));

		List<String> result = redisService.retrieveList("list-key");
		assertNotNull(result);
		assertEquals(3, result.size());
		assertTrue(result.contains("first") && result.contains("second") && result.contains("third"));

		assertNotNull(redisService.saveList(null, Arrays.asList("first", "second")));

		assertNotNull(redisService.saveList("null-key", null));
		assertNotNull(redisService.retrieveList("null-key"));
	}

	@Test
	void testSaveRetrieveValueHashMap() {
		assertNotNull(redisService.saveValueInHashMap("hash-key", "first-key", "first-value"));
		assertNotNull(redisService.saveValueInHashMap("hash-key", "second-key", "second-value"));

		assertEquals("first-value", redisService.retrieveValueFromHashMap("hash-key", "first-key"));
		Map<String, String> result = redisService.retrieveValueFromHashMap("hash-key");
		assertNotNull(result);
		assertEquals(2, result.size());
		assertTrue(result.containsKey("first-key") && result.containsKey("second-key") && result.containsValue("first-value") && result.containsValue("second-value"));

		assertNotNull(redisService.saveValueInHashMap("null-hash", "null-fk", null));
		assertNotNull(redisService.retrieveValueFromHashMap("null-hash", "null-fk"));
	}

	@Test
	void testSaveValueWithExpireDate() {
		assertNotNull(redisService.saveValueWithExpireDate("expire-key", "expire-value", 100L));
		assertEquals("expire-value", redisService.readByKey("expire-key"));
		try {
			Thread.sleep(100005);
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
		assertNull(redisService.readByKey("expire-key"));
	}
}
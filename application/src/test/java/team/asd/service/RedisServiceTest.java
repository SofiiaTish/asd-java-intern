package team.asd.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import team.asd.exception.ValidationException;

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

        assertThrows(ValidationException.class, () -> redisService.saveValueByKey(null, "null-value"));
        assertThrows(ValidationException.class, () -> redisService.saveValueByKey("second-key", null));
    }

    @Test
    void testSaveIntoList() {
        assertNotNull(redisService.saveList("list-key", Arrays.asList("first", "second")));
        assertNotNull(redisService.saveElementIntoList("list-key", "third"));

        List<String> result = redisService.retrieveList("list-key");
        assertNotNull(result);
        assertTrue(result.contains("first") && result.contains("second") && result.contains("third"));

        assertThrows(ValidationException.class, () -> redisService.saveList(null, Arrays.asList("first", "second")));

        assertThrows(ValidationException.class, () -> redisService.saveList("null-key", null));
        assertThrows(ValidationException.class, () -> redisService.saveList("null-key", Arrays.asList("first", null, "second")));
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

        assertThrows(ValidationException.class, () -> redisService.saveValueInHashMap("null-hash", "null-fk", null));
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
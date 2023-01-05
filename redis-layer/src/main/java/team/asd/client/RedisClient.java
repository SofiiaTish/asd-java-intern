package team.asd.client;

import org.springframework.stereotype.Repository;

@Repository
public interface RedisClient {
	String readByKey(String key);
}

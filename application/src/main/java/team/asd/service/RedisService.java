package team.asd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import team.asd.client.RedisClient;
import team.asd.util.ValidationUtil;

import java.util.List;
import java.util.Map;

@Service
public class RedisService {

	private final RedisClient redisClient;


	public RedisService(@Autowired RedisClient redisClient) {
		this.redisClient = redisClient;
	}

	public String readByKey(String key) {
		ValidationUtil.validKey(key);
		return redisClient.readByKey(key);
	}

	public String saveValueByKey(String key, String value) {
		ValidationUtil.validKey(key);
		return redisClient.saveValueByKey(key, value);
	}

	public Long saveList(String keyList, List<String> list) {
		ValidationUtil.validKey(keyList);
		return redisClient.saveList(keyList, list);
	}

	public Long saveElementIntoList(String keyList, String value) {
		ValidationUtil.validKey(keyList);
		return redisClient.saveElementIntoList(keyList, value);
	}

	public List<String> retrieveList(String keyList) {
		ValidationUtil.validKey(keyList);
		return redisClient.retrieveList(keyList);
	}

	public Long saveValueInHashMap(String primaryKey, String secondaryKey, String value) {
		ValidationUtil.validKey(primaryKey);
		ValidationUtil.validKey(secondaryKey);
		return redisClient.saveValueInHashMap(primaryKey, secondaryKey, value);
	}

	public String retrieveValueFromHashMap(String primaryKey, String secondaryKey) {
		ValidationUtil.validKey(primaryKey);
		ValidationUtil.validKey(secondaryKey);
		return redisClient.retrieveValueFromHashMap(primaryKey, secondaryKey);
	}

	public Map<String, String> retrieveValueFromHashMap(String primaryKey) {
		ValidationUtil.validKey(primaryKey);
		return redisClient.retrieveValueFromHashMap(primaryKey);
	}

	public String saveValueWithExpireDate(String key, String value, Long expireDate) {
		ValidationUtil.validKey(key);
		ValidationUtil.validExpireTime(expireDate);
		return redisClient.saveValueWithExpireDate(key, value, expireDate);
	}

}

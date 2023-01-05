package team.asd.client;


import java.util.List;
import java.util.Map;

public interface RedisClient {
	String readByKey(String key);

	String saveValueByKey(String key, String value);

	Long saveList(String keyList, List<String> list);

	Long saveElementIntoList(String keyList, String value);

	List<String> retrieveList(String keyList);

	Long saveValueInHashMap(String primaryKey, String secondaryKey, String value);

	String retrieveValueFromHashMap(String primaryKey, String secondaryKey);

	Map<String, String> retrieveValueFromHashMap(String primaryKey);

	String saveValueWithExpireDate(String key, String value, long expireDate);
}

package team.asd.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.asd.service.RedisService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = {"/redis"})
@ApiOperation("Redis Api")
public class RedisController {

	private final RedisService redisService;


	public RedisController(@Autowired RedisService redisService) {
		this.redisService = redisService;
	}


	@GetMapping(path = {"/value/{key}"})
	public String getValueByKey(@PathVariable String key) {
		return redisService.readByKey(key);
	}

	@GetMapping(path = {"/list/{key}"})
	public List<String> retrieveList(@PathVariable String key) {
		return redisService.retrieveList(key);
	}

	@GetMapping(path = {"/hash-value/keys"})
	public String retrieveValueFromHashMap(
			@RequestParam String primaryKey,
			@RequestParam String secondaryKey) {
		return redisService.retrieveValueFromHashMap(primaryKey, secondaryKey);
	}

	@GetMapping(path = {"/hash-value/{primaryKey}"})
	public Map<String, String> retrieveValueFromHashMap(@PathVariable String primaryKey) {
		return redisService.retrieveValueFromHashMap(primaryKey);
	}

	@PostMapping(path = {"/value"})
	public String storeValueByKey(@RequestBody ObjectNode json) {
		return redisService.saveValueByKey(json.get("key").asText(null), json.get("value").asText(null));
	}

	@PostMapping(path = {"/expire-value"})
	public String saveValueWithExpireDate(@RequestBody ObjectNode json) {
		return redisService.saveValueWithExpireDate(json.get("key").asText(null), json.get("value").asText(null), json.get("expire_date").asLong());
	}

	@PostMapping(path = {"/list"})
	public Long saveList(@RequestBody ObjectNode json) {
		return redisService.saveList(json.get("key").asText(null), json.findValuesAsText("list"));
	}

	@PostMapping(path = {"/list/element"})
	public Long saveElementIntoList(@RequestBody ObjectNode json) {
		return redisService.saveElementIntoList(json.get("key").asText(null), json.get("value").asText(null));
	}

	@PostMapping(path = {"/hash-value"})
	public Long saveValueInHashMap(@RequestBody ObjectNode json) {
		return redisService.saveValueInHashMap(json.get("primary_key").asText(null), json.get("secondary_key").asText(null), json.get("value").asText(null));
	}

}

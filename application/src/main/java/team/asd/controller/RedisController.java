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

	@ApiOperation(value = "Get value by key", notes = "Require String path variable. Return value by key if exists.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Value was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/value/{key}"})
	public String getValueByKey(@PathVariable String key) {
		return redisService.readByKey(key);
	}

	@ApiOperation(value = "Get list by key", notes = "Require String path variable. Return list by key if exists.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "List was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/list/{key}"})
	public List<String> retrieveList(@PathVariable String key) {
		return redisService.retrieveList(key);
	}

	@ApiOperation(value = "Get value from hash map by two keys", notes = "Require two String request params. Return value by keys if exists.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Value was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/hash-value/keys"})
	public String retrieveValueFromHashMap(
			@RequestParam @ApiParam(value = "Hash map key", example = "hash-map") String primaryKey,
			@RequestParam @ApiParam(value = "Hash value key", example = "hash-key") String secondaryKey) {
		return redisService.retrieveValueFromHashMap(primaryKey, secondaryKey);
	}

	@ApiOperation(value = "Get hash map by key", notes = "Require String path variable. Return hash map by key if exists.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Hash map was found"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@GetMapping(path = {"/hash-value/{primaryKey}"})
	public Map<String, String> retrieveValueFromHashMap(@PathVariable String primaryKey) {
		return redisService.retrieveValueFromHashMap(primaryKey);
	}

	@ApiOperation(value = "Store value in redis by key", notes = "Require JSON object with 'key' and 'value' fields. Return OK.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful storage"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/value"})
	public String storeValueByKey(@RequestBody ObjectNode json) {
		return redisService.saveValueByKey(json.get("key").asText(null), json.get("value").asText(null));
	}

	@ApiOperation(value = "Store value in redis by key with expire time", notes = "Require JSON object with 'key', 'value' and 'expire_date' fields. Return OK.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful storage"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/expire-value"})
	public String saveValueWithExpireDate(@RequestBody Map<String, String> json) {
		return redisService.saveValueWithExpireDate(json.get("key"), json.get("value"), json.get("expire_date") == null ? null : Long.valueOf(json.get("expire_date")));
	}

	@ApiOperation(value = "Store list in redis by key", notes = "Require JSON object with 'key' and 'list' fields. Field 'list' consists of values in list form. Return size of list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful storage"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/list"})
	public Long saveList(@RequestBody Map<String, Object> json) {
		return redisService.saveList((String) json.get("key"), (List<String>) json.get("list"));
	}

	@ApiOperation(value = "Store value into list in redis by key", notes = "Require JSON object with 'key' and 'value'. Return size of list.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful storage"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/list/element"})
	public Long saveElementIntoList(@RequestBody ObjectNode json) {
		return redisService.saveElementIntoList(json.get("key").asText(null), json.get("value").asText(null));
	}

	@ApiOperation(value = "Store value into hash map in redis by two keys", notes = "Require JSON object with 'primary_key', 'secondary_key' and 'value'. Keys are kye of map and key of its element .Return size of map.")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successful storage"),
			@ApiResponse(code = 404, message = "Custom message about error reasons or unknown error")
	})
	@PostMapping(path = {"/hash-value"})
	public Long saveValueInHashMap(@RequestBody ObjectNode json) {
		return redisService.saveValueInHashMap(json.get("primary_key").asText(null), json.get("secondary_key").asText(null), json.get("value").asText(null));
	}

}

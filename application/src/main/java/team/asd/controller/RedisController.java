package team.asd.controller;

import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.asd.service.RedisService;

import javax.validation.Valid;

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

	@PostMapping(path = {"/value"})
	public String storeValueByKey(@RequestBody ObjectNode json) {
		return redisService.saveValueByKey(json.get("key").asText(null), json.get("value").asText(null));
	}



}

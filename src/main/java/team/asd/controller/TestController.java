package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import team.asd.entity.Message;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
public class TestController {
	private final String message = "Test message from Spring Boot Application!";
	private final TestMapper testMapper;

	public TestController(@Autowired TestMapper testMapper) {
		this.testMapper = testMapper;
		//testMapper.insertValue("test1");
	}

	@GetMapping("/test/message")
	public Message message() {

		return new Message(LocalDate.now(), message);
	}

	@PostMapping("/test/message")
	public Message message(@RequestBody String testMessage) {
		try {
			return new Message(LocalDate.now().plusDays(Integer.parseInt(testMessage)), message);
		} catch (NumberFormatException e) {
			return new Message(LocalDate.now(), "Error - " + e.getMessage());
		}
	}
	@PutMapping("/test/message")
	public Message messageInsert(@RequestBody String testMessage) {
		try {
			testMapper.insertValue(testMessage);
			return new Message(LocalDate.now(), "Message inserted");
		} catch (NumberFormatException e) {
			return new Message(LocalDate.now(), "Error - " + e.getMessage());
		}
	}

}

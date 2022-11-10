package team.asd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import team.asd.entity.Message;
import team.asd.mapper.TestMapper;

import java.time.LocalDate;

@RestController
public class TestController {
    private final String message = "Test message from Spring Boot Application!";

    /*public TestController(@Autowired TestMapper testMapper){
        testMapper.insertValue("test again");
    }*/

    @GetMapping("/test/message")
    public Message message() {

        return new Message(LocalDate.now(), message);
    }

    @PostMapping("/test/message")
    public Message message(@RequestBody String test_message) {
        try {
            return new Message(LocalDate.now().plusDays(Integer.parseInt(test_message)), message);
        } catch (NumberFormatException e) {
            return new Message(LocalDate.now(), "Error - " + e.getMessage());
        }
    }

}

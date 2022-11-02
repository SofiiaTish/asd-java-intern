package team.asd;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
public class TestController {

    private final String message = "Test message from Spring Boot Application!";

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

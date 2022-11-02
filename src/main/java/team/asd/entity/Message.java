package team.asd.entity;

import java.time.LocalDate;

public class Message {

    private LocalDate date;
    private String message;

    public Message(LocalDate date, String message) {
        this.date = date;
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

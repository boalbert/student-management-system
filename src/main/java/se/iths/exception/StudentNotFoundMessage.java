package se.iths.exception;

import javax.json.bind.annotation.JsonbProperty;

public class StudentNotFoundMessage {
    private Long id;
    private final String message;

    public StudentNotFoundMessage(Long id, String message) {
        this.id = id;
        this.message = message;
    }

    public StudentNotFoundMessage(String message) {
        this.message = message;
    }

    @JsonbProperty("Student ID")
    public Long getId() {
        return id;
    }

    @JsonbProperty("Error Message")
    public String getMessage() {
        return message;
    }
}

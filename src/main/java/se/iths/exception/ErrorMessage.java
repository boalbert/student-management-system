package se.iths.exception;

import javax.json.bind.annotation.JsonbProperty;

public class ErrorMessage {
    private final Long id;
    private final String message;

    public ErrorMessage(Long id, String message) {
        this.id = id;
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

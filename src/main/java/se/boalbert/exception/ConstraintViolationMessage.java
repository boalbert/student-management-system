package se.boalbert.exception;

import javax.json.bind.annotation.JsonbProperty;

public class ConstraintViolationMessage {
    private String property;
    private String message;

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @JsonbProperty("error message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
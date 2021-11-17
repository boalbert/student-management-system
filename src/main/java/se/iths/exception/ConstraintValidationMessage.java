package se.iths.exception;

import javax.json.bind.annotation.JsonbProperty;

public class ConstraintValidationMessage {
    private String property;
    private String message;

    @JsonbProperty("property")
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
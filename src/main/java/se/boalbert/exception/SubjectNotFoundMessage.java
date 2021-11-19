package se.boalbert.exception;

public class SubjectNotFoundMessage {
    private final String message;

    public SubjectNotFoundMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

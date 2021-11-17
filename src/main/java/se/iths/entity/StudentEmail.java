package se.iths.entity;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

public class StudentEmail {
    @Email(regexp = ".+@.+\\..+", message = "Not a valid email adress.")
    @NotEmpty(message = "Must not be empty")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

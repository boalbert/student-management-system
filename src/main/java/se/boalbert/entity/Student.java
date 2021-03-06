package se.boalbert.entity;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@NamedQuery(
        name = "Student.findByLastName",
        query = "SELECT s FROM Student s WHERE s.lastName = :lastName"
)
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Size(min = 2, max = 50, message = "Must be between 2 - 50 characters")
    @NotEmpty(message = "Must not be empty")
    private String firstName;

    @Size(min = 2, max = 50, message = "Must be between 2 - 50 characters")
    @NotEmpty(message = "Must not be empty")
    private String lastName;

    @Email(regexp = ".+@.+\\..+", message = "Not a valid email adress.")
    @NotEmpty(message = "Must not be empty")
    private String email;

    private String phoneNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}

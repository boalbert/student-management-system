package se.boalbert.entity;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(
        name = "Student.findByLastName",
        query = "SELECT s FROM Student s WHERE s.lastName = :lastName"
)
public class Student {

    public Student() {
    }

    public Student(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

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

    @JsonbTransient
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_courses",
            joinColumns = @JoinColumn(name = "subject_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private List<Subject> subjectList = new ArrayList<>();

    public void addSubject(Subject subject) {
        subjectList.add(subject);
        subject.getStudentList().add(this);
    }

    public List<Subject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<Subject> subjectList) {
        this.subjectList = subjectList;
    }

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

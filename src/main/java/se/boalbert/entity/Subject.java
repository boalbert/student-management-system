package se.boalbert.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQuery(name = "subject.findByName", query = "SELECT s FROM Subject s WHERE s.name = :name")
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany(mappedBy = "subjectList", cascade = CascadeType.ALL)
    private List<Student> studentList = new ArrayList<>();

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


}

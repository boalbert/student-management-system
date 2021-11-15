package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class StudentService {

    @PersistenceContext
    EntityManager em;


    public void createStudent(Student student) {
        em.persist(student);
    }

    public Student findById(Long id) {
        return em.find(Student.class, id);
    }

    public List<Student> getAll() {
        TypedQuery<Student> getAllStudents = em.createQuery("SELECT s from Student s", Student.class);
        return getAllStudents.getResultList();
    }

    public boolean delete(Long id) {
        var studentToDelete = findById(id);
        if(studentToDelete != null) {
            em.remove(studentToDelete);
            return true;
        }
        else return false;
    }
}

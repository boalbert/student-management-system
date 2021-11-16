package se.iths.service;


import se.iths.entity.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
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
        if (studentToDelete != null) {
            em.remove(studentToDelete);
            return true;
        } else return false;
    }

    public boolean updateStudent(Long id, Student updatedStudent) {
        var studentToUpdate = findById(id);
        if (studentToUpdate != null) {
            studentToUpdate.setEmail(updatedStudent.getEmail());
            studentToUpdate.setFirstName(updatedStudent.getFirstName());
            studentToUpdate.setLastName(updatedStudent.getLastName());
            studentToUpdate.setPhoneNumber(updatedStudent.getPhoneNumber());
            em.merge(studentToUpdate);

            return true;
        }
        em.persist(updatedStudent);
        return false;
    }

    public URI generateCreatedUri(UriInfo uriInfo, Long id) {
        return uriInfo.getBaseUriBuilder().path(id.toString()).build();
    }

    public boolean updateEmail(Long id, String email) {
        var updateStudentEmail = findById(id);
        if (updateStudentEmail != null) {
            updateStudentEmail.setEmail(email);
            em.merge(updateStudentEmail);
            return true;
        }
        return false;
    }

    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> studentsBylastName
                = em.createQuery("SELECT s FROM Student s WHERE s.lastName = :lastName", Student.class).setParameter("lastName", lastName);
        return studentsBylastName.getResultList();
    }
}

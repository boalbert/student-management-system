package se.boalbert.service;

import se.boalbert.entity.Student;
import se.boalbert.entity.StudentEmail;

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
        }
        return false;
    }

    public boolean update(Long id, Student updatedStudent) {
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

    public boolean replaceEmail(Long id, StudentEmail studentEmail) {
        var updateStudentEmail = findById(id);
        if (updateStudentEmail != null) {
            updateStudentEmail.setEmail(studentEmail.getEmail());
            em.merge(updateStudentEmail);
            return true;
        }
        return false;
    }

    public List<Student> findByLastName(String lastName) {
        TypedQuery<Student> findByLastName =
                em.createNamedQuery("Student.findByLastName", Student.class)
                        .setParameter("lastName", lastName);

        return findByLastName.getResultList();
    }

    public URI generateCreatedUri(UriInfo uriInfo, Long id) {
        return uriInfo.getBaseUriBuilder().path("students/" + id.toString()).build();
    }

}

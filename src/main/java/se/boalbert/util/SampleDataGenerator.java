package se.boalbert.util;

import se.boalbert.entity.Student;
import se.boalbert.entity.Subject;
import se.boalbert.entity.Teacher;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Singleton
@Startup
public class SampleDataGenerator {

    @PersistenceContext
    EntityManager em;

    @PostConstruct
    public void generateData() {

        // Teachers
        var teacherPontus = new Teacher("Pontus", "Redig", "pontus@iths.se", "0782134");
        var teacherMartin = new Teacher("Martin", "Blomgren", "blomman@iths.se", "07998361");

        // Students
        var studentRut = new Student("Rut", "Mahlberg", "ruma@hotmail.com", "09876542");
        var studentGustav = new Student("Gustav", "Larsson", "gula@gmail.com", "079987621");
        var studentAlva = new Student("Alva", "Myrdal", "myllan@hotmail.com", "07795412");

        // Subjects
        var java = new Subject("Java");
        var python = new Subject("Python");
        var react = new Subject("React");
        var rust = new Subject("Rust");
        var flutter = new Subject("Flutter");
        var lisp = new Subject("Lisp");
        
        teacherPontus.addSubject(java);
        teacherPontus.addSubject(python);
        teacherPontus.addSubject(react);

        teacherMartin.addSubject(rust);
        teacherMartin.addSubject(flutter);
        teacherMartin.addSubject(lisp);

        studentRut.addSubject(java);
        studentRut.addSubject(python);
        studentRut.addSubject(lisp);

        studentGustav.addSubject(react);
        studentGustav.addSubject(rust);
        studentGustav.addSubject(flutter);

        studentAlva.addSubject(java);
        studentAlva.addSubject(rust);
        studentAlva.addSubject(lisp);

        em.persist(teacherPontus);
        em.persist(teacherMartin);

        em.persist(studentRut);
        em.persist(studentGustav);
        em.persist(studentAlva);
    }
}

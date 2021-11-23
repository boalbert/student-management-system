package se.boalbert.service;

import se.boalbert.entity.Subject;
import se.boalbert.exception.SubjectNotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class SubjectService {

    @PersistenceContext
    EntityManager em;

    public List<Subject> getAll() {
        TypedQuery<Subject> getAllSubjects = em.createQuery("SELECT s from Subject s", Subject.class);
        return getAllSubjects.getResultList();
    }

    public Subject findSubjectByName(String subjectName) {
        TypedQuery<Subject> findSubject
                = em.createNamedQuery("subject.findByName", Subject.class)
                .setParameter("name", subjectName);

        return findSubject.getResultStream()
                .findFirst()
                .orElseThrow(() -> new SubjectNotFoundException(subjectName));
    }
}

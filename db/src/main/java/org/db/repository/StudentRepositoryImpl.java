package org.db.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.db.model.Student;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    private EntityManager em;

    @Override
    public Student save(final Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        em.refresh(student.getUser());
        return student;
    }

}

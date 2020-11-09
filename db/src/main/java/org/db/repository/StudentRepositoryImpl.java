package org.db.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.db.model.Student;
import org.db.model.User;

@Repository
public class StudentRepositoryImpl implements StudentRepository {

    @Autowired
    EntityManager em;

    @Override
    public Student findById(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteById(Long id) {
        // TODO Auto-generated method stub

    }

    @Override
    public Student save(Student student) {
        if (student.getId() == null) {
            em.persist(student);
        } else {
            em.merge(student);
        }
        em.refresh(student.getUser());
        return student;
    }

}

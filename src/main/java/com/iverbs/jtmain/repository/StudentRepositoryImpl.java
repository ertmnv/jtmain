package com.iverbs.jtmain.repository;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iverbs.jtmain.model.Student;
import com.iverbs.jtmain.model.User;

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

package com.iverbs.jtmain.repository;

import com.iverbs.jtmain.model.Student;

public interface StudentRepository {

    Student findById(Long id);

    void deleteById(Long id);

    Student save(Student student);

}

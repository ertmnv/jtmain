package org.db.repository;

import org.db.model.Student;

public interface StudentRepository {

    Student findById(Long id);

    void deleteById(Long id);

    Student save(Student student);

}

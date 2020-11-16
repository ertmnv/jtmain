package org.db.repository;

import org.db.model.Student;
/**
 * @author snavrockiy
 *
 *         Repository that handles actions related to enrolling and leaving
 *         courses.
 */
public interface StudentRepository {

    /**
     * Saves a given student.
     *
     * @param student the student that should be saved
     * @return saved student
     */
    Student save(Student student);

}

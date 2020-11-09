package org.services;

import java.security.Principal;

import org.db.model.Course;




public interface StudentService {

    void enrollCourse(Long courseId, Principal principal);

    void leaveCourse(Long courseId, Principal principal);

    void deleteStudentFromCourse(Course course);

}

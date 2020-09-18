package com.iverbs.jtmain.service;

import java.security.Principal;

import com.iverbs.jtmain.model.Course;

public interface StudentService {

    void enrollCourse(Long courseId, Principal principal);

    void leaveCourse(Long courseId, Principal principal);

    void deleteStudentFromCourse(Course course);

}

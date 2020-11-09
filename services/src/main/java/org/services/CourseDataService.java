package org.services;

import java.util.List;

import org.db.model.Course;
import org.springframework.data.domain.Page;



public interface CourseDataService {

    Page<Course> getAllCourses(Integer page, Integer size);

    Course createCourse(Course course, Long authorId);

    List<Course> getAllCoursesManagedByAuthor(Long authorId);

    Course editCourse(Course course, Long authorId);

    void deleteCourse(Long courseId);

    Course findById(Long courseId);

    List<Course> getAllCoursesTakenByStudent(Long studentId);

    Long getNumberOfCourses();

}

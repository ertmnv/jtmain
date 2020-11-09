package org.services;

import java.util.Collection;
import java.util.List;

import org.db.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface CourseService {

    List<Course> getAllCourses(Integer page, Integer size);

    Course createCourse(Course course, Long authorId);

    List<Course> getAllCoursesManagedByAuthor(Long authorId);

    Course editCourse(Course course, Long authorId);

    void deleteCourse(Long courseId);

    Course findById(Long courseId);

    Collection<Course> getAllCoursesTakenByStudent(Long studentId);

    Long getNumberOfCourses();

}

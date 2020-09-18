package com.iverbs.jtmain.service;

import java.util.List;
import org.springframework.data.domain.Page;
import com.iverbs.jtmain.model.Course;


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

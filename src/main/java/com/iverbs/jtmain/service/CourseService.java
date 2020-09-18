package com.iverbs.jtmain.service;

import java.util.Collection;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.iverbs.jtmain.dto.CourseAndStudentNameDto;
import com.iverbs.jtmain.model.Course;

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

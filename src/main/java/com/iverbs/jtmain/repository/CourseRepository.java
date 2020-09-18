package com.iverbs.jtmain.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.iverbs.jtmain.dto.CourseDto;
import com.iverbs.jtmain.model.Course;

public interface CourseRepository {

    Course findById(Long id);

    void deleteById(Long id);

    Course save(Course course);

    List<Course> findAll(Integer page, Integer size);

    List<Course> getAllCoursesManagedByAuthor(Long authorId);

    Collection<Course> getAllCoursesTakenByStudent(Long studentId);

    Long getNumberOfCourses();

    Map<Long, List<String>> getStudentNameByCourse();
}

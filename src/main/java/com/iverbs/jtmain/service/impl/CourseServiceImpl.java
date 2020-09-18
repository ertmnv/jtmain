package com.iverbs.jtmain.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.dto.CourseAndStudentNameDto;
import com.iverbs.jtmain.model.Author;
import com.iverbs.jtmain.model.Course;
import com.iverbs.jtmain.model.Student;
import com.iverbs.jtmain.repository.AuthorRepository;
import com.iverbs.jtmain.repository.CourseRepository;
import com.iverbs.jtmain.service.AuthorService;
import com.iverbs.jtmain.service.CourseService;
import com.iverbs.jtmain.service.StudentService;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private AuthorService authorService;

    @Autowired
    private StudentService studentService;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    CacheManager cacheManager;

    @Override

    public List<Course> getAllCourses(Integer page, Integer size) {
        Map<Long, List<String>> courseIdstudentName = courseRepository.getStudentNameByCourse();
        List<Course> courses = courseRepository.findAll(page, size);
        return courses;

    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public Course createCourse(Course course, Long authorId) {
        Author author = authorService.findById(authorId);
        course.setAuthor(author);
        courseRepository.save(course);
        return course;
    }

    @Override
    @Cacheable(value = "courses")
    public List<Course> getAllCoursesManagedByAuthor(Long authorId) {
        return courseRepository.getAllCoursesManagedByAuthor(authorId);
    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public Course editCourse(Course course, Long authorId) {
        Course originCourse = courseRepository.findById(course.getId());
        originCourse.setName(course.getName());
        return originCourse;
    }

    @Override
    @Transactional
    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId);
        List<Student> students = course.getStudents();
        students.forEach(student -> student.getCourses().remove(course));
        courseRepository.deleteById(courseId);
    }

    @Override
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    public Collection<Course> getAllCoursesTakenByStudent(Long studentId) {
        return courseRepository.getAllCoursesTakenByStudent(studentId);
    }

    @Override
    public Long getNumberOfCourses() {
        // TODO Auto-generated method stub
        return courseRepository.getNumberOfCourses();
    }

}

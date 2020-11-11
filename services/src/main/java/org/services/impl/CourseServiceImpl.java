package org.services.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.db.model.Author;
import org.db.model.Course;
import org.db.model.Student;
import org.db.repository.CourseRepository;
import org.services.AuthorService;
import org.services.CourseService;
import org.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;



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
    @Transactional(propagation=Propagation.REQUIRED)
    public List<Course> getAllCourses(Integer page, Integer size) {
        Map<Long, List<String>> courseIdstudentName = courseRepository.getStudentNameByCourse();
        List<Course> courses = courseRepository.findAll(page, size);
        return courses;

    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
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
    @Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value = "courses", allEntries = true)
    public Course editCourse(Course course, Long authorId) {
        Course originCourse = courseRepository.findById(course.getId());
        originCourse.setName(course.getName());
        return originCourse;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    @CacheEvict(value = "courses", allEntries = true)
    public void deleteCourse(Long courseId) {
        Course course = courseRepository.findById(courseId);
        List<Student> students = course.getStudents();
        students.forEach(student -> student.getCourses().remove(course));
        courseRepository.deleteById(courseId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Course findById(Long courseId) {
        return courseRepository.findById(courseId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Collection<Course> getAllCoursesTakenByStudent(Long studentId) {
        return courseRepository.getAllCoursesTakenByStudent(studentId);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public Long getNumberOfCourses() {
        // TODO Auto-generated method stub
        return courseRepository.getNumberOfCourses();
    }

}

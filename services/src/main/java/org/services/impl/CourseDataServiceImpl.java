package org.services.impl;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.db.model.Author;
import org.db.model.Course;
import org.db.model.Student;
import org.db.repository.springdata.CourseDataRepository;
import org.services.AuthorService;
import org.services.CourseDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class CourseDataServiceImpl implements CourseDataService {

    @Autowired
    private AuthorService authorService;

    @Autowired
    private CourseDataRepository courseDataRepository;

    @Override
    @Transactional
    public Course createCourse(Course course, Long authorId) {
        Author author = authorService.findById(authorId);
        course.setAuthor(author);
        courseDataRepository.save(course);
        return course;
    }

    @Override
    public List<Course> getAllCoursesManagedByAuthor(Long authorId) {
        // TODO Auto-generated method stub
        return courseDataRepository.findByAuthorId(authorId);
    }
    
    @Override
    @Transactional
    public Course editCourse(Course course, Long authorId) {
        Optional<Course> originCourse = courseDataRepository.findById(course.getId());
        // String SQL = "update course set name = ? where id = ?";
        // jdbcTemplate.update(SQL, "iii", course.getId());
        originCourse.get().setName(course.getName());
        // courseRepository.save(originCourse);
        return originCourse.get();
    }

    @Override
    @Transactional
    public void deleteCourse(Long courseId) {
        Optional<Course> course = courseDataRepository.findById(courseId);
        List<Student> students = course.get().getStudents();
        students.forEach(student -> student.getCourses().remove(course));
        courseDataRepository.deleteById(courseId);
    }

    public Course findById(Long courseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Course> getAllCoursesTakenByStudent(Long studentId) {
        // TODO Auto-generated method stub
        return courseDataRepository.allStudentsInCourse(studentId);
    }

    @Override
    @Transactional
    public Long getNumberOfCourses() {
        List<Object[]> numberOfCourses = courseDataRepository.getNumberOfCourses();
        return (Long) numberOfCourses.get(0)[0];
    }

    @Override
    public Page<Course> getAllCourses(Integer page, Integer size) {
        Pageable paging = PageRequest.of(page, size, Sort.by("name"));
        return courseDataRepository.findAll(paging);
    }

}

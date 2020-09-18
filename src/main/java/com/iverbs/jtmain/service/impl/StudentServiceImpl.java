package com.iverbs.jtmain.service.impl;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.model.Course;
import com.iverbs.jtmain.model.Student;
import com.iverbs.jtmain.model.User;
import com.iverbs.jtmain.repository.StudentRepository;
import com.iverbs.jtmain.service.CourseService;
import com.iverbs.jtmain.service.StudentService;
import com.iverbs.jtmain.service.UserService;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    StudentRepository studentRepository;

    @Override
    @Transactional
    public void enrollCourse(Long courseId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Course course = courseService.findById(courseId);
        Student student;
        if (user.getStudent() == null) {
            student = createStudent(user);
        } else {
            student = user.getStudent();
        }
        student.addCourse(course);
        studentRepository.save(student);
    }

    public Student createStudent(User user) {
        Student student = new Student();
        student.setUser(user);
        return student;
    }

    @Override
    @Transactional
    public void leaveCourse(Long courseId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Course course = courseService.findById(courseId);
        Student student = user.getStudent();
        student.removeCourse(course);
        studentRepository.save(student);
    }

    @Override
    @Transactional
    public void deleteStudentFromCourse(Course course) {
        List<Student> students = course.getStudents();
        students.forEach(student -> student.getCourses().remove(course));
    }

}

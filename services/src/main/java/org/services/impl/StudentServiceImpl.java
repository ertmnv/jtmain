package org.services.impl;

import java.security.Principal;
import java.util.List;

import org.db.model.Course;
import org.db.model.Student;
import org.db.model.User;
import org.db.repository.StudentRepository;
import org.services.CourseService;
import org.services.StudentService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(propagation = Propagation.REQUIRED)
public class StudentServiceImpl implements StudentService {

    @Autowired
    private CourseService courseService;

    @Autowired
    UserService userService;

    @Autowired
    StudentRepository studentRepository;

    @Override
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
    public void leaveCourse(Long courseId, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        Course course = courseService.findById(courseId);
        Student student = user.getStudent();
        student.removeCourse(course);
        studentRepository.save(student);
    }

    @Override
    public void deleteStudentFromCourse(Course course) {
        List<Student> students = course.getStudents();
        students.forEach(student -> student.getCourses().remove(course));
    }

}

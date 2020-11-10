package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.api.services.CourseApiService;
import org.db.dto.CourseDto;
import org.db.dto.PageForCourses;
import org.db.model.Course;
import org.services.CourseService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class CourseApiServiceImpl implements CourseApiService {
    
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userservice;

    @Override
    public ResponseEntity createCourse(Course course, Long authorId) {
        // TODO @CurrentUser User user - should be used instead authorId
        Course createdCourse = courseService.createCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", createdCourse.toCourseDto());
        return ResponseEntity.ok(response);
    }

    @Override
    public List<CourseDto> getAllCoursesManagedByAuthor(Long authorId) {
        return courseService.getAllCoursesManagedByAuthor(authorId).stream().map(course -> course.toCourseDto())
                .collect(Collectors.toList());
    }

    @Override
    public List<CourseDto> getAllCoursesTakenByStudent(Long studentId) {
        return courseService.getAllCoursesTakenByStudent(studentId).stream().map(course -> course.toCourseDto())
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity editCourse(Course course, Long authorId) {
        Course editedCourse = courseService.editCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", editedCourse.toCourseDto());
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity deleteCourse(Long courseId, Principal principal) {
        // CR1: seems, the best candidate for this stuff is move it to api services where you can
        // check student's access to cource or lesson. Be aware - do not use current user principal in business
        // services. There can be situations when you don't have a current user (for eg background tasks).
        // TODO before deletion we should check that currently logged user has course
        // which is being passed as parameter
        // Long authorId =
        // userservice.findByUsername(principal.getName()).getAuthor().getId();
        courseService.deleteCourse(courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "course with id" + courseId + "was deleted");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<PageForCourses> getAllCoursesByPage(Integer page, Integer size) {
        PageForCourses pageForCourses = new PageForCourses();
        pageForCourses.setContent(courseService.getAllCourses(page, size).parallelStream()
                .map(course -> course.toCourseDto()).collect(Collectors.toList()));
        pageForCourses.setTotalElements(courseService.getNumberOfCourses());
        return new ResponseEntity<PageForCourses>(pageForCourses, new HttpHeaders(), HttpStatus.OK);
    }

}

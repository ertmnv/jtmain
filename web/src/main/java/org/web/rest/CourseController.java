package org.web.rest;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseApiService courseApiService;

    @PostMapping("/courses/{authorId}")
    ResponseEntity createCourse(@Valid @RequestBody Course course, @PathVariable Long authorId) {
        return courseApiService.createCourse(course, authorId);
    }

    @GetMapping("/courses/{authorId}")
    List<CourseDto> getAllCoursesManagedByAuthor(@PathVariable Long authorId) {
        return courseApiService.getAllCoursesManagedByAuthor(authorId);
    }

    @GetMapping("/coursesbystudent/{studentId}")
    List<CourseDto> getAllCoursesTakenByStudent(@PathVariable Long studentId) {
        return courseApiService.getAllCoursesTakenByStudent(studentId);
    }

    @PatchMapping("/courses/{authorId}")
    ResponseEntity editCourse(@RequestBody Course course, Principal principal) {
        return courseApiService.editCourse(course, principal);
    }

    @DeleteMapping("/courses/{courseId}")
    ResponseEntity deleteCourse(@PathVariable Long courseId, Principal principal) {
        return courseApiService.deleteCourse(courseId, principal);
    }

    @GetMapping("/courses")
    ResponseEntity<PageForCourses> getAllCoursesByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        return courseApiService.getAllCoursesByPage(page, size);
    }

}

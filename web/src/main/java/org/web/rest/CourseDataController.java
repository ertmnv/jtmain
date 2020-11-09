package org.web.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.db.dto.CourseDto;
import org.db.dto.PageForCourses;
import org.db.model.Course;
import org.services.CourseDataService;
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
@RequestMapping("/api/v2")
public class CourseDataController {

    @Autowired
    private CourseDataService courseService;

    @Autowired
    private UserService userservice;

    @GetMapping("/courses/{authorId}")
    List<CourseDto> getAllCoursesManagedByAuthor(@PathVariable Long authorId) {
        return courseService.getAllCoursesManagedByAuthor(authorId).stream().map(course -> course.toCourseDto())
                .collect(Collectors.toList());
    }

    @PostMapping("/courses/{authorId}")
    ResponseEntity createCourse(@Valid @RequestBody Course course, @PathVariable Long authorId) {
        Course createdCourse = courseService.createCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", createdCourse.toCourseDto());
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/courses/{authorId}")
    ResponseEntity editCourse(@RequestBody Course course, @PathVariable Long authorId) {
        Course editedCourse = courseService.editCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", editedCourse.toCourseDto());
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/courses/{courseId}")
    ResponseEntity deleteCourse(@PathVariable Long courseId, Principal principal) {
        // Long authorId =
        // userservice.findByUsername(principal.getName()).getAuthor().getId();
        courseService.deleteCourse(courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "course with id" + courseId + "was deleted");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/courses")
    ResponseEntity<PageForCourses> getAllCoursesByPage(@RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "2") Integer size) {
        PageForCourses pageForCourses = new PageForCourses();
        pageForCourses
                .setContent(courseService.getAllCourses(page, size).map(course -> course.toCourseDto()).getContent());
        pageForCourses.setTotalElements(courseService.getNumberOfCourses());
        return new ResponseEntity<PageForCourses>(pageForCourses, new HttpHeaders(), HttpStatus.OK);
    }

    @GetMapping("/coursesbystudent/{studentId}")
    List<CourseDto> getAllCoursesTakenByStudent(@PathVariable Long studentId) {
        return courseService.getAllCoursesTakenByStudent(studentId).stream().map(course -> course.toCourseDto())
                .collect(Collectors.toList());
    }

}

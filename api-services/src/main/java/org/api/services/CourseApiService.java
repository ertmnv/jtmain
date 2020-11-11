package org.api.services;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;

public interface CourseApiService {

    ResponseEntity createCourse(Course course, Long authorId);

    List<CourseDto> getAllCoursesManagedByAuthor(Long authorId);

    List<CourseDto> getAllCoursesTakenByStudent(Long studentId);

    ResponseEntity editCourse(Course course, Principal principal);
    
    ResponseEntity deleteCourse(Long courseId, Principal principal);
    
    ResponseEntity<PageForCourses> getAllCoursesByPage(Integer page, Integer size);

}

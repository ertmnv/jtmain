package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.api.services.CourseApiService;
import org.api.services.mappers.CourseMapper;
import org.api.services.shared.PermissionDeniedException;
import org.db.dto.CourseDto;
import org.db.dto.PageForCourses;
import org.db.model.Course;
import org.mapstruct.factory.Mappers;
import org.services.CourseService;
import org.services.PermissionCheckService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    
    @Autowired
    PermissionCheckService permissionCheckService;

    @Override
    @Transactional
    public ResponseEntity createCourse(Course course, Long authorId) {
        // TODO @CurrentUser User user - should be used instead authorId
        Course createdCourse = courseService.createCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", createdCourse.toCourseDto());
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public List<CourseDto> getAllCoursesManagedByAuthor(Long authorId) {
        CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class );
        return courseService.getAllCoursesManagedByAuthor(authorId).stream().map(course -> INSTANCE.courseToCourseDto(course))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CourseDto> getAllCoursesTakenByStudent(Long studentId) {
        CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class );
        return courseService.getAllCoursesTakenByStudent(studentId).stream().map(course -> INSTANCE.courseToCourseDto(course))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity editCourse(Course course, Principal principal) {
        CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class );
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteCoursePermission(authorId, course.getId())) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        Course editedCourse = courseService.editCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", INSTANCE.courseToCourseDto(editedCourse));
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity deleteCourse(Long courseId, Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteCoursePermission(authorId, courseId)) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        courseService.deleteCourse(courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "course with id" + courseId + "was deleted");
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<PageForCourses> getAllCoursesByPage(Integer page, Integer size) {
        CourseMapper INSTANCE = Mappers.getMapper( CourseMapper.class );
        PageForCourses pageForCourses = new PageForCourses();
        pageForCourses.setContent(courseService.getAllCourses(page, size).parallelStream()
                .map(course -> INSTANCE.courseToCourseDto(course)).collect(Collectors.toList()));
        pageForCourses.setTotalElements(courseService.getNumberOfCourses());
        return new ResponseEntity<PageForCourses>(pageForCourses, new HttpHeaders(), HttpStatus.OK);
    }

}

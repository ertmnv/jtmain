package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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


@Service
public class CourseApiServiceImpl implements CourseApiService {

    private CourseMapper mapperInstance = Mappers.getMapper(CourseMapper.class);

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userservice;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Override
    @Transactional
    public ResponseEntity createCourse(final Course course, final Long authorId) {
        Course createdCourse = courseService.createCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", mapperInstance.courseToCourseDto(createdCourse));
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public List<CourseDto> getAllCoursesManagedByAuthor(final Long authorId) {
        return courseService.getAllCoursesManagedByAuthor(authorId).stream()
                .map(course -> mapperInstance.courseToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CourseDto> getAllCoursesTakenByStudent(final Long studentId) {
        return courseService.getAllCoursesTakenByStudent(studentId).stream()
                .map(course -> mapperInstance.courseToCourseDto(course)).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ResponseEntity editCourse(final Course course, final Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteCoursePermission(authorId, course.getId())) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        Course editedCourse = courseService.editCourse(course, authorId);
        Map<Object, Object> response = new HashMap<>();
        response.put("course", mapperInstance.courseToCourseDto(editedCourse));
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity deleteCourse(final Long courseId, final Principal principal) {
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
    public ResponseEntity<PageForCourses> getAllCoursesByPage(final Integer page, final Integer size) {
        PageForCourses pageForCourses = new PageForCourses();
        pageForCourses.setContent(courseService.getAllCourses(page, size).parallelStream()
                .map(course -> mapperInstance.courseToCourseDto(course)).collect(Collectors.toList()));
        pageForCourses.setTotalElements(courseService.getNumberOfCourses());
        return new ResponseEntity<PageForCourses>(pageForCourses, new HttpHeaders(), HttpStatus.OK);
    }

}

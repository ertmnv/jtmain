package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.api.services.StudentApiService;
import org.db.model.User;
import org.security.services.jwt.JwtTokenProvider;
import org.services.StudentService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class StudentApiServiceImpl implements StudentApiService {

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private UserService userService;

    public ResponseEntity enrollCourse(final Long courseId, final Principal principal) {
        User user = userService.findByUsername(principal.getName());
        studentService.enrollCourse(courseId, user);
        String token = jwtTokenProvider.createToken(principal.getName(), user);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    public ResponseEntity leaveCourse(final Long courseId, final Principal principal) {
        User user = userService.findByUsername(principal.getName());
        studentService.leaveCourse(courseId, user);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "you leave course with id" + courseId);
        return ResponseEntity.ok(response);
    }
}

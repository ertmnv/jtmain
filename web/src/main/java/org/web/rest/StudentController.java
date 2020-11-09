package org.web.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.db.model.User;
import org.security.services.jwt.JwtTokenProvider;
import org.services.StudentService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserService userService;

    @GetMapping("students/{courseId}")
    public ResponseEntity enrollCourse(@PathVariable Long courseId, Principal principal) {
        studentService.enrollCourse(courseId, principal);
        User user = userService.findByUsername(principal.getName());
        String token = jwtTokenProvider.createToken(principal.getName(), user);
        Map<Object, Object> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @GetMapping("studentsleavecourse/{courseId}")
    public ResponseEntity leaveCourse(@PathVariable Long courseId, Principal principal) {
        studentService.leaveCourse(courseId, principal);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "you leave course with id" + courseId);
        return ResponseEntity.ok(response);
    }

}

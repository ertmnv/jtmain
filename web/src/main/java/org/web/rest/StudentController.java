package org.web.rest;

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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentApiService studentApiService;

    @GetMapping("students/{courseId}")
    public ResponseEntity enrollCourse(@PathVariable Long courseId, Principal principal) {
        return studentApiService.enrollCourse(courseId, principal);
    }

    @GetMapping("studentsleavecourse/{courseId}")
    public ResponseEntity leaveCourse(@PathVariable Long courseId, Principal principal) {
        return studentApiService.enrollCourse(courseId, principal);
    }

}

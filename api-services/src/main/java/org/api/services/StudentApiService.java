package org.api.services;

import java.security.Principal;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

public interface StudentApiService {

    ResponseEntity enrollCourse(Long courseId, Principal principal);
    
    ResponseEntity leaveCourse(Long courseId, Principal principal);
}

package org.web.rest;

import java.security.Principal;
import org.api.services.StudentApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to enrolling and leaving
 *         courses. Per rules in {@link org.security.config.SecurityConfig},
 *         authentication is required for all requests.
 */
@RestController
@RequestMapping("/api/v1")
public class StudentController {

    @Autowired
    private StudentApiService studentApiService;

    /**
     * Enrolls to course.
     *
     * @param courseId  the identifier of the course, student will enroll to
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains token which was
     *         updated according to student role and the {@linkplain HttpStatus#OK
     *         OK} status
     *
     */
    @GetMapping("students/{courseId}")
    public ResponseEntity enrollCourse(@PathVariable final Long courseId, final Principal principal) {
        return studentApiService.enrollCourse(courseId, principal);
    }

    /**
     * Excludes a student from course.
     *
     * @param courseId  the identifier of the course, student will exclude from
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @GetMapping("studentsleavecourse/{courseId}")
    public ResponseEntity leaveCourse(@PathVariable final Long courseId, final Principal principal) {
        return studentApiService.enrollCourse(courseId, principal);
    }

}

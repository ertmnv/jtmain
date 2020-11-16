package org.api.services;

import java.security.Principal;

import org.springframework.http.ResponseEntity;

/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to enrolling and leaving
 *         courses.
 */
public interface StudentApiService {

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
    ResponseEntity enrollCourse(Long courseId, Principal principal);

    /**
     * Excludes a student from course.
     *
     * @param courseId  the identifier of the course, student will exclude from
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity leaveCourse(Long courseId, Principal principal);
}

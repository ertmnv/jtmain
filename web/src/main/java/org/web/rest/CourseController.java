package org.web.rest;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.api.services.CourseApiService;
import org.db.dto.CourseDto;
import org.db.dto.PageForCourses;
import org.db.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to creating, editing,
 *         obtaining and deleting courses. Per rules in
 *         {@link org.security.config.SecurityConfig}, authentication is
 *         required for all requests.
 */
@RestController
@RequestMapping("/api/v1")
public class CourseController {

    @Autowired
    private CourseApiService courseApiService;

    /**
     * Creates course.
     *
     * @param course the course to create.
     * @param authorId the identifier of the author who will own the created course
     * @return {@code ResponseEntity} with body that contains created course and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PostMapping("/courses/{authorId}")
    ResponseEntity createCourse(@Valid @RequestBody final Course course, @PathVariable final Long authorId) {
        return courseApiService.createCourse(course, authorId);
    }

    /**
     * Returns all courses managed by Author whose identifier is provided.
     *
     * @param authorId the identifier of the author whose courses are to be returned
     * @return list of courses
     */
    @GetMapping("/courses/{authorId}")
    List<CourseDto> getAllCoursesManagedByAuthor(@PathVariable final Long authorId) {
        return courseApiService.getAllCoursesManagedByAuthor(authorId);
    }

    /**
     * Returns all courses taken by student whose identifier is provided.
     *
     * @param studentId the identifier of the student, courses taken by this student
     *                  will be returned
     * @return list of courses
     */
    @GetMapping("/coursesbystudent/{studentId}")
    List<CourseDto> getAllCoursesTakenByStudent(@PathVariable final Long studentId) {
        return courseApiService.getAllCoursesTakenByStudent(studentId);
    }

    /**
     * Edits provided course.
     *
     * @param course    the course which will be edited
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains edited course and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PatchMapping("/courses/{authorId}")
    ResponseEntity editCourse(@RequestBody final Course course, final Principal principal) {
        return courseApiService.editCourse(course, principal);
    }

    /**
     * Deletes course.
     *
     * @param courseId  the identifier of the course, which should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @DeleteMapping("/courses/{courseId}")
    ResponseEntity deleteCourse(@PathVariable final Long courseId, final Principal principal) {
        return courseApiService.deleteCourse(courseId, principal);
    }

    /**
     * Returns a structure similar to Page of entities meeting the paging
     * restrictions provided in page and size.
     *
     * @param page number of page that should be returned
     * @param size number of elements in the page
     * @return structure similar to Page
     */
    @GetMapping("/courses")
    ResponseEntity<PageForCourses> getAllCoursesByPage(
            @RequestParam(name = "page", defaultValue = "0") final Integer page,
            @RequestParam(defaultValue = "2") final Integer size) {
        return courseApiService.getAllCoursesByPage(page, size);
    }

}

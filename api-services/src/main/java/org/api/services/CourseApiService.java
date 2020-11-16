package org.api.services;

import java.security.Principal;
import java.util.List;
import org.db.dto.CourseDto;
import org.db.dto.PageForCourses;
import org.db.model.Course;
import org.springframework.http.ResponseEntity;

/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to creating, editing,
 *         obtaining and deleting courses.
 */
public interface CourseApiService {

    /**
     * Creates course.
     *
     * @param course   the course to create.
     * @param authorId the identifier of the author who will own the created course
     * @return {@code ResponseEntity} with body that contains created course and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity createCourse(Course course, Long authorId);

    /**
     * Returns all courses managed by Author whose identifier is provided.
     *
     * @param authorId the identifier of the author whose courses are to be returned
     * @return list of courses
     */
    List<CourseDto> getAllCoursesManagedByAuthor(Long authorId);

    /**
     * Returns all courses taken by student whose identifier is provided.
     *
     * @param studentId the identifier of the student, courses taken by this student
     *                  will be returned
     * @return list of courses
     */
    List<CourseDto> getAllCoursesTakenByStudent(Long studentId);

    /**
     * Edits provided course.
     *
     * @param course    the course which will be edited
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains edited course and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity editCourse(Course course, Principal principal);

    /**
     * Deletes course.
     *
     * @param courseId  the identifier of the course, which should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity deleteCourse(Long courseId, Principal principal);

    /**
     * Returns a structure similar to Page of entities meeting the paging
     * restrictions provided in page and size.
     *
     * @param page number of page that should be returned
     * @param size number of elements in the page
     * @return structure similar to Page
     */
    ResponseEntity<PageForCourses> getAllCoursesByPage(Integer page, Integer size);

}

package org.services;

import java.util.Collection;
import java.util.List;

import org.db.model.Course;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to creating, editing, obtaining
 *         and deleting courses. Per rules in
 *         {@link org.security.config.SecurityConfig}, authentication is
 *         required for all requests.
 */
public interface CourseService {

    /**
     * Returns list of courses meeting the paging restrictions provided through page
     * and size.
     *
     * @param page number of page that should be returned
     * @param size number of courses in the page
     * @return list of courses
     */
    List<Course> getAllCourses(Integer page, Integer size);

    /**
     * Creates courses. {@link javax.persistence.EntityManager#persist} is used as
     * implementation.
     *
     * @param course   the course to create
     * @param authorId the identifier of the author who will own the created course
     * @return created course
     */
    Course createCourse(Course course, Long authorId);

    /**
     * Returns all courses managed by Author whose identifier is provided.
     *
     * @param authorId the identifier of the author whose courses are to be returned
     * @return list of courses
     */
    List<Course> getAllCoursesManagedByAuthor(Long authorId);

    /**
     * Edits provided course.
     *
     * @param course   the course which will be edited
     * @param authorId the identifier of the author who own the course
     * @return edited course
     */
    Course editCourse(Course course, Long authorId);

    /**
     * Deletes course. {@link javax.persistence.EntityManager#remove} is used as
     * implementation.
     *
     * @param courseId  the identifier of the course, which should be deleted
     * @param principal the principal of currently logged in user
     */
    void deleteCourse(Long courseId);

    /**
     * Finds by primary key. {@link javax.persistence.EntityManager#find} is used as
     * implementation.
     *
     * @param courseId the identifier of course to search for
     * @return found course
     */
    Course findById(Long courseId);

    /**
     * Returns all courses taken by student whose identifier is provided.
     *
     * @param studentId the identifier of the student, courses taken by this student
     *                  will be returned
     * @return list of courses
     */
    Collection<Course> getAllCoursesTakenByStudent(Long studentId);

    /**
     * Returns number of existing courses.
     *
     * @return number of existing courses
     */
    Long getNumberOfCourses();

}

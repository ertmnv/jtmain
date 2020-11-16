package org.services;

import org.db.model.Course;
import org.db.model.User;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to enrolling and leaving
 *         courses.
 */
public interface StudentService {

    /**
     * Enrolls student to course.
     *
     * @param courseId the identifier of the course, student will enroll to
     * @param user     the currently logged in user
     */
    void enrollCourse(Long courseId, User user);

    /**
     * Excludes a student from course.
     *
     * @param courseId the identifier of the course, student will exclude from
     * @param user     currently logged in user
     */
    void leaveCourse(Long courseId, User user);

    /**
     * Excludes all students from course.
     *
     * @param course
     */
    void deleteStudentFromCourse(Course course);

}

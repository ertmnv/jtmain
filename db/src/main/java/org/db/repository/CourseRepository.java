package org.db.repository;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.db.model.Course;

/**
 * @author snavrockiy
 *
 *         Repository that handles actions related to creating, editing,
 *         obtaining and deleting courses.
 */
public interface CourseRepository {

    /**
     * Retrieves a course by its identifier.
     *
     * @param id the identifier of the course that should be retrieved.
     * @return found course
     */
    Course findById(Long id);

    /**
     * Deletes the course with the given identifier.
     *
     * @param id the identifier of the course that should be deleted.
     */
    void deleteById(Long id);

    /**
     * Saves a given course.
     *
     * @param course the course that should be saved
     * @return saved course
     */
    Course save(Course course);

    /**
     * Returns courses meeting paging restriction provided through page and size.
     *
     * @param page the page that should be returned
     * @param size the number of elements on the page
     * @return list of courses
     */
    List<Course> findAll(Integer page, Integer size);

    /**
     * Retrieves all courses managed by Author whose identifier is provided.
     *
     * @param authorId the identifier of the author whose courses are to be returned
     * @return list of courses
     */
    List<Course> getAllCoursesManagedByAuthor(Long authorId);

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

    /**
     * Returns Map where identifier of the course is key and list of students which
     * take this course is value.
     *
     * @return Map where identifier of the course is key and list of students which
     *         take this course is value.
     */
    Map<Long, List<String>> getStudentNameByCourse();

    /**
     * Returns number of courses by given course identifier and author identifier.
     *
     * @param authorId the identifier of the author whose courses should be taken
     *                 into account
     * @param courseId the identifier of the course that should be used for
     *                 filtering
     * @return count of the course
     */
    int getCourseCountByCourseIdAndAuthorId(Long authorId, Long courseId);

    /**
     * Returns number of courses by given lesson identifier and author identifier.
     *
     * @param authorId the identifier of the author whose courses should be taken
     *                 into account
     * @param courseId the identifier of the lesson that should be used for
     *                 filtering
     * @return count of the course
     */
    int getCourseCountByLessonIdAndAuthorId(Long authorId, Long lessonId);

    /**
     * Returns number of courses by given section identifier and author identifier.
     *
     * @param authorId the identifier of the author whose courses should be taken
     *                 into account
     * @param courseId the identifier of the section that should be used for
     *                 filtering
     * @return count of the course
     */
    int getCourseCountBySectionIdAndAuthorId(Long authorId, Long sectionId);
}

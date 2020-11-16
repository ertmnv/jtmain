package org.services;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to checking permissions of user.
 */
public interface PermissionCheckService {

    /**
     * Tests if author has permission to update or delete course.
     *
     * @param authorId the identifier of the author that try to perform update or
     *                 delete operations
     * @param courseId the identifier of the course that is being updated or deleted
     * @return <tt>true</tt> if author has permission to perform update and delete
     *         operations
     */
    boolean doesUserHaveUpdateDeleteCoursePermission(Long authorId, Long courseId);

    /**
     * Tests if author has permission to update or delete lesson.
     *
     * @param authorId the identifier of the author that try to perform update or
     *                 delete operations
     * @param lessonId the identifier of the lesson that is being updated or deleted
     * @return <tt>true</tt> if author has permission to perform update and delete
     *         operations
     */
    boolean doesUserHaveUpdateDeleteLessonPermission(Long authorId, Long lessonId);

    /**
     * Tests if author has permission to update or delete section.
     *
     * @param authorId the identifier of the author that try to perform update or
     *                 delete operations
     * @param sectionId the identifier of the section that is being updated or deleted
     * @return <tt>true</tt> if author has permission to perform update and delete
     *         operations
     */
    boolean doesUserHaveUpdateDeleteSectionPermission(Long authorId, Long sectionId);

}

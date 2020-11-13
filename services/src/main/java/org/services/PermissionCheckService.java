package org.services;

public interface PermissionCheckService {
    
    boolean doesUserHaveUpdateDeleteCoursePermission(Long authorId, Long courseId);

    boolean doesUserHaveUpdateDeleteLessonPermission(Long authorId, Long lessonId);

    boolean doesUserHaveUpdateDeleteSectionPermission(Long authorId, Long sectionId);

}

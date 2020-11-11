package org.services;

public interface PermissionCheckService {
    
    boolean doesUserHaveUpdateDeleteCoursePermission(Long authorId, Long courseId);

}

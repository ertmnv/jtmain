package org.services.impl;

import org.db.repository.CourseRepository;
import org.services.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionCheckServiceImpl implements PermissionCheckService {

    @Autowired
    CourseRepository courseRepository;
    
    @Override
    public boolean doesUserHaveUpdateDeleteCoursePermission(Long authorId, Long courseId) {
        
        return courseRepository.getCourseCountByCourseIdAndAuthorId(authorId, courseId) >= 1;
    }

}

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

    @Override
    public boolean doesUserHaveUpdateDeleteLessonPermission(Long authorId, Long lessonId) {
        return courseRepository.getCourseCountByLessonIdAndAuthorId(authorId, lessonId) >= 1;
    }

    @Override
    public boolean doesUserHaveUpdateDeleteSectionPermission(Long authorId, Long sectionId) {
        return courseRepository.getCourseCountBySectionIdAndAuthorId(authorId, sectionId) >= 1;
    }

}

package org.services.impl;

import org.db.repository.CourseRepository;
import org.services.PermissionCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionCheckServiceImpl implements PermissionCheckService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    public boolean doesUserHaveUpdateDeleteCoursePermission(final Long authorId, final Long courseId) {

        return courseRepository.getCourseCountByCourseIdAndAuthorId(authorId, courseId) >= 1;
    }

    @Override
    public boolean doesUserHaveUpdateDeleteLessonPermission(final Long authorId, final Long lessonId) {
        return courseRepository.getCourseCountByLessonIdAndAuthorId(authorId, lessonId) >= 1;
    }

    @Override
    public boolean doesUserHaveUpdateDeleteSectionPermission(final Long authorId, final Long sectionId) {
        return courseRepository.getCourseCountBySectionIdAndAuthorId(authorId, sectionId) >= 1;
    }

}

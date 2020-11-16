package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import org.api.services.LessonApiService;
import org.api.services.shared.PermissionDeniedException;
import org.db.model.Lesson;
import org.services.LessonService;
import org.services.PermissionCheckService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LessonApiServiceImpl implements LessonApiService {

    @Autowired
    private LessonService lessonService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private UserService userservice;

    public ResponseEntity createLesson(final Lesson lesson, final Long sectionId) {
        lessonService.createLesson(lesson, sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was created");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity updateLeson(final Lesson lesson, final Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteLessonPermission(authorId, lesson.getId())) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        lessonService.editLesson(lesson);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was updated");
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity deleteLesson(final Long lessonId, final Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteLessonPermission(authorId, lessonId)) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        lessonService.deleteLesson(lessonId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was deleted");
        return ResponseEntity.ok(response);
    }

}

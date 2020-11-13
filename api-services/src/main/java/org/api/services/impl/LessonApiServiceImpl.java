package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.api.services.LessonApiService;
import org.api.services.shared.PermissionDeniedException;
import org.db.model.Lesson;
import org.services.CourseService;
import org.services.LessonService;
import org.services.PermissionCheckService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class LessonApiServiceImpl implements LessonApiService {

    @Autowired
    LessonService lessonService;
    
    @Autowired
    PermissionCheckService permissionCheckService;

    @Autowired
    private UserService userservice;
    
    public ResponseEntity createLesson(Lesson lesson, Long sectionId) {
        lessonService.createLesson(lesson, sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was created");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity updateLeson(Lesson lesson, Principal principal) {
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
    public ResponseEntity deleteLesson(Long lessonId, Principal principal) {
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

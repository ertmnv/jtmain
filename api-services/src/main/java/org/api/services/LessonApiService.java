package org.api.services;

import java.security.Principal;

import javax.validation.Valid;

import org.db.model.Lesson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface LessonApiService {
    
    ResponseEntity createLesson(Lesson lesson, Long sectionId);
    
    ResponseEntity deleteLesson(Long lessonId, Principal principal);
    
    ResponseEntity updateLeson(Lesson lesson, Principal principal);

}

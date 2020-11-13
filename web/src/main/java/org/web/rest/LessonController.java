package org.web.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.api.services.LessonApiService;
import org.db.model.Lesson;
import org.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



// CR1: Could you lease add the validation that student may change only its own courses, lessons, etc?
@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    LessonApiService lessonApiService;

    @PostMapping("/lessons/{sectionId}")
    ResponseEntity createLesson(@Valid @RequestBody Lesson lesson, @PathVariable Long sectionId) {
        return lessonApiService.createLesson(lesson, sectionId);
    }

    @DeleteMapping("/lessons/{lessonId}")
    ResponseEntity deleteLesson(@PathVariable Long lessonId, Principal principal) {
        return lessonApiService.deleteLesson(lessonId, principal);
    }

    @PatchMapping("/lessons")
    ResponseEntity updateLeson(@RequestBody Lesson lesson, Principal principal) {
        return lessonApiService.updateLeson(lesson, principal);
    }

}

package com.iverbs.jtmain.rest;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iverbs.jtmain.model.Lesson;
import com.iverbs.jtmain.service.LessonService;

@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    LessonService lessonService;

    @PostMapping("/lessons/{sectionId}")
    ResponseEntity createLesson(@Valid @RequestBody Lesson lesson, @PathVariable Long sectionId) {
        lessonService.createLesson(lesson, sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was created");
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/lessons/{lessonId}")
    ResponseEntity deleteLesson(@PathVariable Long lessonId) {
        lessonService.deleteLesson(lessonId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was deleted");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/lessons")
    ResponseEntity updateLeson(@RequestBody Lesson lesson) {
        lessonService.editLesson(lesson);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "lesson was updated");
        return ResponseEntity.ok(response);
    }

}

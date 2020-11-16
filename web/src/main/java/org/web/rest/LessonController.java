package org.web.rest;

import java.security.Principal;

import javax.validation.Valid;

import org.api.services.LessonApiService;
import org.db.model.Lesson;
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
/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to creating, editing,
 *         obtaining and deleting lessons. Per rules in
 *         {@link org.security.config.SecurityConfig}, authentication is
 *         required for all requests.
 */
@RestController
@RequestMapping("/api/v1")
public class LessonController {

    @Autowired
    private LessonApiService lessonApiService;

    /**
     * Creates lesson.
     *
     * @param lesson    the lesson to create
     * @param sectionId the identifier of the section that will own the created
     *                  lesson
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PostMapping("/lessons/{sectionId}")
    ResponseEntity createLesson(@Valid @RequestBody final Lesson lesson, @PathVariable final Long sectionId) {
        return lessonApiService.createLesson(lesson, sectionId);
    }

    /**
     * Deletes lesson.
     *
     * @param lessonId  the identifier of the lesson that should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @DeleteMapping("/lessons/{lessonId}")
    ResponseEntity deleteLesson(@PathVariable final Long lessonId, final Principal principal) {
        return lessonApiService.deleteLesson(lessonId, principal);
    }

    /**
     * Edits lesson.
     *
     * @param lesson    the lesson to edit
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PatchMapping("/lessons")
    ResponseEntity updateLeson(@RequestBody final Lesson lesson, final Principal principal) {
        return lessonApiService.updateLeson(lesson, principal);
    }

}

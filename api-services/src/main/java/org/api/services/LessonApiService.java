package org.api.services;

import java.security.Principal;



import org.db.model.Lesson;
import org.springframework.http.ResponseEntity;


/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to creating, editing,
 *         obtaining and deleting lessons.
 */
public interface LessonApiService {

    /**
     * Creates lesson.
     *
     * @param lesson    the lesson to create
     * @param sectionId the identifier of the section that will own the created
     *                  lesson
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity createLesson(Lesson lesson, Long sectionId);

    /**
     * Deletes lesson.
     *
     * @param lessonId  the identifier of the lesson that should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity deleteLesson(Long lessonId, Principal principal);

    /**
     * Edits lesson.
     *
     * @param lesson    the lesson to edit
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity updateLeson(Lesson lesson, Principal principal);

}

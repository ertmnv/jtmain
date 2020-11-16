package org.services;

import org.db.model.Lesson;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to creating, editing, obtaining
 *         and deleting lessons.
 */
public interface LessonService {

    /**
     * Creates lesson. {@link javax.persistence.EntityManager#persist} is used as
     * implementation.
     *
     * @param lesson    the lesson to create
     * @param sectionId the identifier of the section that will own the created
     *                  lesson
     * @return created lesson
     */
    Lesson createLesson(Lesson lesson, Long sectionId);

    /**
     * Edits lesson. {@link javax.persistence.EntityManager#merge} is used as
     * implementation.
     *
     * @param lesson    the lesson to edit
     * @param principal the principal of currently logged in user
     * @return edited lesson
     */
    Lesson editLesson(Lesson lesson);

    /**
     * Deletes lesson. {@link javax.persistence.EntityManager#remove} is used as
     * implementation.
     *
     * @param lessonId the identifier of the lesson that should be deleted
     */
    void deleteLesson(Long lessonId);

}

package org.db.repository;

import java.util.List;

import org.db.model.Lesson;

/**
 * @author snavrockiy
 *
 *         Repository that handles actions related to creating, editing,
 *         obtaining and deleting lessons.
 */
public interface LessonRepository {

    /**
     * Retrieves a lesson by its identifier.
     *
     * @param id the identifier of the lesson that should be retrieved.
     * @return found lesson
     */
    Lesson findById(Long id);

    /**
     * Deletes the lesson with the given identifier.
     *
     * @param id the identifier of the lesson that should be deleted.
     */
    void deleteById(Long id);

    /**
     * Saves a given lesson.
     *
     * @param lesson the lesson that should be saved
     * @return saved lesson
     */
    Lesson save(Lesson lesson);

    /**
     * Returns all lessons.
     *
     * @return list of lessons
     */
    List<Lesson> findAll();

    /**
     * Retrieves all lessons that are included in the section whose identifier is
     * provided.
     *
     * @param sectionId
     * @return list of lessons
     */
    List<Lesson> getAllLessonsBySection(Long sectionId);

}

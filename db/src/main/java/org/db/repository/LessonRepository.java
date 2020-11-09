package org.db.repository;

import java.util.List;

import org.db.model.Lesson;

public interface LessonRepository {

    Lesson findById(Long id);

    void deleteById(Long id);

    Lesson save(Lesson lesson);

    List<Lesson> findAll();

    List<Lesson> getAllLessonsBySection(Long lessonId);

}

package org.services;

import java.util.List;

import org.db.model.Lesson;



public interface LessonService {

    List<Lesson> getAllLessons();

    Lesson createLesson(Lesson lesson, Long sectionId);

    List<Lesson> getAllLessonsBySection(Long sectionId);

    Lesson editLesson(Lesson Lesson);

    void deleteLesson(Long lessonId);

}

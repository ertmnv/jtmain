package org.services.impl;

import org.db.model.Lesson;
import org.db.model.Section;
import org.db.repository.LessonRepository;
import org.db.repository.SectionRepository;
import org.services.LessonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class LessonServiceImpl implements LessonService {

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private LessonRepository lessonRepository;

    @Override
    public Lesson createLesson(final Lesson lesson, final Long sectionId) {
        Section section = sectionRepository.findById(sectionId);
        lesson.setSection(section);
        lessonRepository.save(lesson);
        return lesson;
    }

    @Override
    public Lesson editLesson(final Lesson lesson) {
        Lesson originalLesson = lessonRepository.findById(lesson.getId());
        originalLesson.setName(lesson.getName());
        lessonRepository.save(originalLesson);
        return originalLesson;
    }

    @Override
    public void deleteLesson(final Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

}

package org.services.impl;

import java.util.List;

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
    public List<Lesson> getAllLessons() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Lesson createLesson(Lesson lesson, Long sectionId) {
        Section section = sectionRepository.findById(sectionId);
        lesson.setSection(section);
        lessonRepository.save(lesson);
        return lesson;
    }

    @Override
    public List<Lesson> getAllLessonsBySection(Long sectionId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Lesson editLesson(Lesson lesson) {
        Lesson originalLesson = lessonRepository.findById(lesson.getId());
        originalLesson.setName(lesson.getName());
        lessonRepository.save(originalLesson);
        return originalLesson;
    }

    @Override
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

}

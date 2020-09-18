package com.iverbs.jtmain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.model.Lesson;
import com.iverbs.jtmain.model.Section;
import com.iverbs.jtmain.repository.LessonRepository;
import com.iverbs.jtmain.repository.SectionRepository;
import com.iverbs.jtmain.service.LessonService;

@Service
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
    @Transactional
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
    @Transactional
    public Lesson editLesson(Lesson lesson) {
        Lesson originalLesson = lessonRepository.findById(lesson.getId());
        originalLesson.setName(lesson.getName());
        lessonRepository.save(originalLesson);
        return originalLesson;
    }

    @Override
    @Transactional
    public void deleteLesson(Long lessonId) {
        lessonRepository.deleteById(lessonId);
    }

}

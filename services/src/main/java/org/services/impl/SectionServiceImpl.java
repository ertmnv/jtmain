package org.services.impl;

import java.util.List;

import org.db.model.Course;
import org.db.model.Section;
import org.db.repository.CourseRepository;
import org.db.repository.SectionRepository;
import org.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

// CR1: you can consider to apply @Transactional to class level
@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SectionServiceImpl implements SectionService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> getAllSections() {
        return sectionRepository.findAll();
    }

    @Override
    public Section createSection(final Section section, final Long courseId) {
        Course course = courseRepository.findById(courseId);
        section.setCourse(course);
        Section createdSection = sectionRepository.save(section);
        return createdSection;
    }

    @Override
    public Section editSection(final Section section) {
        Section originSection = sectionRepository.findById(section.getId());
        originSection.setName(section.getName());
        sectionRepository.save(originSection);
        return originSection;
    }

    @Override
    public void deleteSection(final Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }

    @Override
    public List<Section> getAllSectionsByCourse(final Long courseId) {
        return sectionRepository.getAllSectionsByCourse(courseId);
    }
}

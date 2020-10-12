package com.iverbs.jtmain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.iverbs.jtmain.model.Course;
import com.iverbs.jtmain.model.Section;
import com.iverbs.jtmain.repository.CourseRepository;
import com.iverbs.jtmain.repository.SectionRepository;
import com.iverbs.jtmain.service.SectionService;

// CR1: you can consider to apply @Transactional to class level
@Service
public class SectionServiceImpl implements SectionService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Override
    public List<Section> getAllSections() {
        // CR1: what does it mean (stub)?
        // TODO Auto-generated method stub
        return sectionRepository.findAll();
    }

    @Override
    @Transactional
    public Section createSection(Section section, Long courseId) {
        Course course = courseRepository.findById(courseId);
        section.setCourse(course);
        Section createdSection = sectionRepository.save(section);
        return createdSection;
    }

    @Override
    @Transactional
    public Section editSection(Section section) {
        Section originSection = sectionRepository.findById(section.getId());
        originSection.setName(section.getName());
        sectionRepository.save(originSection);
        return originSection;
    }

    @Override
    @Transactional
    public void deleteSection(Long sectionId) {
        sectionRepository.deleteById(sectionId);
    }

    @Override
    @Transactional
    public List<Section> getAllSectionsByCourse(Long courseId) {
        return sectionRepository.getAllSectionsByCourse(courseId);
    }
}

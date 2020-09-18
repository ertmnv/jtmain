package com.iverbs.jtmain.service;

import java.util.List;

import com.iverbs.jtmain.model.Section;

public interface SectionService {

    List<Section> getAllSections();

    Section createSection(Section section, Long courseId);

    Section editSection(Section section);

    void deleteSection(Long sectionId);

    List<Section> getAllSectionsByCourse(Long courseId);

}

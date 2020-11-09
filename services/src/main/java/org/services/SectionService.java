package org.services;

import java.util.List;

import org.db.model.Section;



public interface SectionService {

    List<Section> getAllSections();

    Section createSection(Section section, Long courseId);

    Section editSection(Section section);

    void deleteSection(Long sectionId);

    List<Section> getAllSectionsByCourse(Long courseId);

}

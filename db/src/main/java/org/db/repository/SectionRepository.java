package org.db.repository;

import java.util.List;


import org.db.model.Section;

public interface SectionRepository {

    Section findById(Long id);

    void deleteById(Long id);

    Section save(Section section);

    List<Section> findAll();

    List<Section> getAllSectionsByCourse(Long courseId);

}

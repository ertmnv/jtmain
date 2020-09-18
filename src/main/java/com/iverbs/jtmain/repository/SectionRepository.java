package com.iverbs.jtmain.repository;

import java.util.List;

import com.iverbs.jtmain.dto.SectionDto;
import com.iverbs.jtmain.model.Section;

public interface SectionRepository {

    Section findById(Long id);

    void deleteById(Long id);

    Section save(Section section);

    List<Section> findAll();

    List<Section> getAllSectionsByCourse(Long courseId);

}

package org.api.services;

import java.security.Principal;
import java.util.List;



import org.db.dto.SectionDto;
import org.db.model.Section;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;


public interface SectionApiService {

    ResponseEntity createSection(Section section, Long courseId);

    List<SectionDto> getAllSections(Pageable pageable);

    ResponseEntity deleteSection(Long sectionId, Principal principal);

    ResponseEntity editSection(Section section, Principal principal);

    List<SectionDto> getAllSectionsByCourse(Long courseId);

}

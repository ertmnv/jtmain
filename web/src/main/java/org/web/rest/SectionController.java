package org.web.rest;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

import org.api.services.SectionApiService;
import org.api.services.mappers.CourseMapper;
import org.api.services.mappers.SectionMapper;
import org.db.dto.SectionDto;
import org.db.model.Section;
import org.mapstruct.factory.Mappers;
import org.services.SectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class SectionController {

    @Autowired
    private SectionApiService sectionApiService;

    @PostMapping("/sections/{courseId}")
    ResponseEntity createSection(@Valid @RequestBody Section section, @PathVariable Long courseId) {
        return sectionApiService.createSection(section, courseId);
    }

    @GetMapping("/sections")
    List<SectionDto> getAllSections(Pageable pageable) {
        return sectionApiService.getAllSections(pageable);
    }

    @DeleteMapping("/sections/{sectionId}")
    ResponseEntity deleteSection(@PathVariable Long sectionId, Principal principal) {
        return sectionApiService.deleteSection(sectionId, principal);
    }

    @PatchMapping("/sections")
    ResponseEntity editSection(@RequestBody Section section, Principal principal) {
        return sectionApiService.editSection(section, principal);
    }

    @GetMapping("/sections/{courseId}")
    List<SectionDto> getAllSectionsByCourse(@PathVariable Long courseId) {
        return sectionApiService.getAllSectionsByCourse(courseId);
    }

}

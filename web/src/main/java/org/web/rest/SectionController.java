package org.web.rest;


import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;

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
    private SectionService sectionService;

    @PostMapping("/sections/{courseId}")
    ResponseEntity createSection(@Valid @RequestBody Section section, @PathVariable Long courseId) {
        // TODO @CurrentUser User user - should be used instead authorId
        Section createdSection = sectionService.createSection(section, courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was created");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sections")
    List<SectionDto> getAllSections(Pageable pageable) {
        // CR1 for this purpose I would recommend to create api service and specific converter to convert
        //  business entity into dto. Ideally controllers must know only api services interfaces and not know any
        //  info about business layer.
        // Also I would recommend to create transaction in apilayer to have an ability to perform complex operations
        // which involves multiple business services.
        SectionMapper INSTANCE = Mappers.getMapper( SectionMapper.class );
        return sectionService.getAllSections().stream().map(section -> INSTANCE.sectionToSectionDto(section))
                .collect(Collectors.toList());
    }

    @DeleteMapping("/sections/{sectionId}")
    ResponseEntity deleteSection(@PathVariable Long sectionId, Principal principal) {
        sectionService.deleteSection(sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was deleted");
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/sections")
    ResponseEntity editSection(@RequestBody Section section) {
        Section editedSection = sectionService.editSection(section);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", editedSection.toSectionDto());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/sections/{courseId}")
    List<SectionDto> getAllSectionsByCourse(@PathVariable Long courseId) {
        return sectionService.getAllSectionsByCourse(courseId).stream().map(section -> section.toSectionDto())
                .collect(Collectors.toList());
    }

}

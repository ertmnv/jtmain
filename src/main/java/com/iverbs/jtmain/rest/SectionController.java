package com.iverbs.jtmain.rest;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

import com.iverbs.jtmain.dto.CourseDto;
import com.iverbs.jtmain.dto.SectionDto;
import com.iverbs.jtmain.model.Course;
import com.iverbs.jtmain.model.Section;
import com.iverbs.jtmain.service.SectionService;

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
        return sectionService.getAllSections().stream().map(section -> section.toSectionDto())
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

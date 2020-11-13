package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.api.services.SectionApiService;
import org.api.services.mappers.SectionMapper;
import org.api.services.shared.PermissionDeniedException;
import org.db.dto.SectionDto;
import org.db.model.Section;
import org.mapstruct.factory.Mappers;
import org.services.PermissionCheckService;
import org.services.SectionService;
import org.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Transactional
public class SectionApiServiceImpl implements SectionApiService {
    
    SectionMapper INSTANCE = Mappers.getMapper(SectionMapper.class);
    
    @Autowired
    private SectionService sectionService;
    
    @Autowired
    PermissionCheckService permissionCheckService;

    @Autowired
    private UserService userservice;

    public ResponseEntity createSection(Section section, Long courseId) {
        Section createdSection = sectionService.createSection(section, courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was created");
        return ResponseEntity.ok(response);
    }

    public List<SectionDto> getAllSections(Pageable pageable) {
        // CR1 for this purpose I would recommend to create api service and specific converter to convert
        //  business entity into dto. Ideally controllers must know only api services interfaces and not know any
        //  info about business layer.
        // Also I would recommend to create transaction in apilayer to have an ability to perform complex operations
        // which involves multiple business services.
        return sectionService.getAllSections().stream().map(section -> INSTANCE.sectionToSectionDto(section))
                .collect(Collectors.toList());
    }

    public ResponseEntity deleteSection(Long sectionId, Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteSectionPermission(authorId, sectionId)) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        sectionService.deleteSection(sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was deleted");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity editSection(Section section, Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteSectionPermission(authorId, section.getId())) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        Section editedSection = sectionService.editSection(section);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", INSTANCE.sectionToSectionDto(editedSection));
        return ResponseEntity.ok(response);
    }

    public List<SectionDto> getAllSectionsByCourse(Long courseId) {
        return sectionService.getAllSectionsByCourse(courseId).stream().map(section -> INSTANCE.sectionToSectionDto(section))
                .collect(Collectors.toList());
    }
}

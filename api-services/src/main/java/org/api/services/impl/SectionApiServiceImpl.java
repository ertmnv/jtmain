package org.api.services.impl;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


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
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class SectionApiServiceImpl implements SectionApiService {

    private SectionMapper mapperInstance = Mappers.getMapper(SectionMapper.class);

    @Autowired
    private SectionService sectionService;

    @Autowired
    private PermissionCheckService permissionCheckService;

    @Autowired
    private UserService userservice;

    // CR2: For Api service is better to work with dtos, even for crud operation.
    // And it would be great to see bean validation on each request dto before it comes to service api layer...
    // This CR is for all controllers.
    public ResponseEntity createSection(final Section section, final Long courseId) {
        Section createdSection = sectionService.createSection(section, courseId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was created");
        return ResponseEntity.ok(response);
    }

    public List<SectionDto> getAllSections() {
        // CR1 for this purpose I would recommend to create api service and specific
        // converter to convert
        // business entity into dto. Ideally controllers must know only api services
        // interfaces and not know any
        // info about business layer.
        // Also I would recommend to create transaction in apilayer to have an ability
        // to perform complex operations
        // which involves multiple business services.
        return sectionService.getAllSections().stream().map(section -> mapperInstance.sectionToSectionDto(section))
                .collect(Collectors.toList());
    }

    public ResponseEntity deleteSection(final Long sectionId, final Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteSectionPermission(authorId, sectionId)) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        sectionService.deleteSection(sectionId);
        Map<Object, Object> response = new HashMap<>();
        response.put("message", "section was deleted");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity editSection(final Section section, final Principal principal) {
        Long authorId = userservice.findByUsername(principal.getName()).getAuthor().getId();
        if (!permissionCheckService.doesUserHaveUpdateDeleteSectionPermission(authorId, section.getId())) {
            throw new PermissionDeniedException("author doesn't own provided course");
        }
        Section editedSection = sectionService.editSection(section);
        Map<Object, Object> response = new HashMap<>();
        response.put("content", mapperInstance.sectionToSectionDto(editedSection));
        return ResponseEntity.ok(response);
    }

    public List<SectionDto> getAllSectionsByCourse(final Long courseId) {
        return sectionService.getAllSectionsByCourse(courseId).stream()
                .map(section -> mapperInstance.sectionToSectionDto(section)).collect(Collectors.toList());
    }
}

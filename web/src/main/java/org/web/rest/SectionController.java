package org.web.rest;

import java.security.Principal;
import java.util.List;
import javax.validation.Valid;
import org.api.services.SectionApiService;
import org.db.dto.SectionDto;
import org.db.model.Section;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author snavrockiy
 *
 *         Controller that handles actions related to creating, editing,
 *         obtaining and deleting sections. Per rules in
 *         {@link org.security.config.SecurityConfig}, authentication is
 *         required for all requests.
 */
@RestController
@RequestMapping("/api/v1")
public class SectionController {

    @Autowired
    private SectionApiService sectionApiService;

    /**
     * Creates section.
     *
     * @param section  the section to create
     * @param courseId the identifier of the course that will own the created
     *                 section
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PostMapping("/sections/{courseId}")
    ResponseEntity createSection(@Valid @RequestBody final Section section, @PathVariable final Long courseId) {
        return sectionApiService.createSection(section, courseId);
    }

    /**
     * Returns all sections.
     *
     * @return List of sections
     */
    @GetMapping("/sections")
    List<SectionDto> getAllSections() {
        return sectionApiService.getAllSections();
    }

    /**
     * Delete sections.
     *
     * @param sectionId the identifier of the section that should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @DeleteMapping("/sections/{sectionId}")
    ResponseEntity deleteSection(@PathVariable final Long sectionId, final Principal principal) {
        return sectionApiService.deleteSection(sectionId, principal);
    }

    /**
     * Edits section.
     *
     * @param section   the section to edit
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains edited section and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    @PatchMapping("/sections")
    ResponseEntity editSection(@RequestBody final Section section, final Principal principal) {
        return sectionApiService.editSection(section, principal);
    }

    /**
     * Returns all sections which belong to course whose identifier is provided.
     *
     * @param courseId the identifier of the course whose sections are to be
     *                 returned
     * @return list of sections
     */
    @GetMapping("/sections/{courseId}")
    List<SectionDto> getAllSectionsByCourse(@PathVariable final Long courseId) {
        return sectionApiService.getAllSectionsByCourse(courseId);
    }

}

package org.api.services;

import java.security.Principal;
import java.util.List;

import org.db.dto.SectionDto;
import org.db.model.Section;
import org.springframework.http.ResponseEntity;

/**
 * @author snavrockiy
 *
 *         Api-service that handles actions related to creating, editing,
 *         obtaining and deleting sections.
 */
public interface SectionApiService {

    /**
     * Creates section.
     *
     * @param section  the section to create
     * @param courseId the identifier of the course that will own the created
     *                 section
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity createSection(Section section, Long courseId);

    /**
     * Returns all sections.
     *
     * @return List of sections
     */
    List<SectionDto> getAllSections();

    /**
     * Delete sections.
     *
     * @param sectionId the identifier of the section that should be deleted
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains message and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity deleteSection(Long sectionId, Principal principal);

    /**
     * Edits section.
     *
     * @param section   the section to edit
     * @param principal the principal of currently logged in user
     * @return {@code ResponseEntity} with body that contains edited section and the
     *         {@linkplain HttpStatus#OK OK} status
     */
    ResponseEntity editSection(Section section, Principal principal);

    /**
     * Returns all sections which belong to course whose identifier is provided.
     *
     * @param courseId the identifier of the course whose sections are to be
     *                 returned
     * @return list of sections
     */
    List<SectionDto> getAllSectionsByCourse(Long courseId);

}

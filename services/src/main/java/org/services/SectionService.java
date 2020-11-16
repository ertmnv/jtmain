package org.services;

import java.util.List;

import org.db.model.Section;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to creating, editing, obtaining
 *         and deleting sections.
 */
public interface SectionService {

    /**
     * Returns all sections.
     *
     * @return List of sections
     */
    List<Section> getAllSections();

    /**
     * Creates section. {@link javax.persistence.EntityManager#persist} is used as
     * implementation.
     *
     * @param section  the section to create
     * @param courseId the identifier of the course that will own the created
     *                 section
     * @return created section
     */
    Section createSection(Section section, Long courseId);

    /**
     * Edits section. {@link javax.persistence.EntityManager#merge} is used as
     * implementation.
     *
     * @param section the section to edit
     * @return edited section
     */
    Section editSection(Section section);

    /**
     * Delete sections. {@link javax.persistence.EntityManager#remove} is used as
     * implementation.
     *
     * @param sectionId the identifier of the section that should be deleted
     */
    void deleteSection(Long sectionId);

    /**
     * Returns all sections which belong to course whose identifier is provided.
     *
     * @param courseId the identifier of the course whose sections are to be
     *                 returned
     * @return list of sections
     */
    List<Section> getAllSectionsByCourse(Long courseId);

}

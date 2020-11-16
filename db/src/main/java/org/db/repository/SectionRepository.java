package org.db.repository;

import java.util.List;

import org.db.model.Section;

/**
 * @author snavrockiy
 *
 *         Repository that handles actions related to creating, editing,
 *         obtaining and deleting sections.
 */
public interface SectionRepository {

    /**
     * Retrieves a section by its identifier.
     *
     * @param id the identifier of the section that should be retrieved.
     * @return found section
     */
    Section findById(Long id);

    /**
     * Deletes the section with the given identifier.
     *
     * @param id the identifier of the section that should be deleted.
     */
    void deleteById(Long id);

    /**
     * Saves a given section.
     *
     * @param section the section that should be saved
     * @return saved section
     */
    Section save(Section section);

    /**
     * Returns all section.
     *
     * @return list of section
     */
    List<Section> findAll();

    /**
     * Retrieves all sections that are included in the course whose identifier is
     * provided.
     *
     * @param courseId
     * @return list of sections
     */
    List<Section> getAllSectionsByCourse(Long courseId);

}

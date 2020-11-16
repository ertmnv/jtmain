package org.db.repository;

import org.db.model.Author;

/**
 * @author snavrockiy
 *
 *         Repository that handles actions related to registering and finding
 *         author.
 */
public interface AuthorRepository {

    /**
     * Saves a given author.
     *
     * @param author the author that should saved
     * @return
     */
    Author save(Author author);

    /**
     * Retrieves an author by its identifier.
     *
     * @param id the identifier of the author that should be retrieved
     * @return found author
     */
    Author findById(Long id);
}

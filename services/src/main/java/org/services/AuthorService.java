package org.services;

import org.db.model.Author;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to registering and finding
 *         author.
 */
public interface AuthorService {

    /**
     * Registers the user as an author and publish
     * {@link org.services.events.AuthorIntroductionEvent} that causes sending an
     * email to the user that notifies the user that he had received author
     * privileges.
     *
     * @param username the name f the user that should be registered a author
     * @return registered author
     */
    Author register(String username);

    /**
     * Returns author by given identifier.
     *
     * @param id the identifier if the author that should be returned
     * @return found author
     */
    Author findById(Long id);

}

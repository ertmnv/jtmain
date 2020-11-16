package org.services;

import java.util.List;

import org.db.model.User;

/**
 * @author snavrockiy
 *
 *         Service that handles actions related to registering, deleting and finding
 *         user.
 */
public interface UserService {

    /**
     * Registers a user with role - ROLE_USER by default
     *
     * @param user the user to register
     * @return registered user
     */
    User register(User user);

    /**
     * Returns all users.
     *
     * @return list of user
     */
    List<User> getAll();

    /**
     * Returns user by name.
     *
     * @param username the name of user that should be returned
     * @return the found user
     */
    User findByUsername(String username);

    /**
     * Returns user by identifier
     *
     * @param id the identifier of the user that should be returned
     * @return found user
     */
    User findById(Long id);

    /**
     * Deletes user by identifier.
     *
     * @param id the identifier of the user that should be deleted
     */
    void delete(long id);
}

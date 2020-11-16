package org.db.repository.springdata;

import org.db.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author snavrockiy
 *
 *         Repository handles actions related to creating, obtaining, updating
 *         and deleting users.
 *
 */
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Retrieves user by its name.
     *
     * @param name the name of the user that should be retrieved
     * @return found user
     */
    User findByUsername(String name);
}

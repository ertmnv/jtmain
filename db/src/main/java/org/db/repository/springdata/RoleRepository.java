package org.db.repository.springdata;

import org.db.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author snavrockiy
 *
 *         Repository handles actions related to creating, obtaining, updating
 *         and deleting user's roles.
 *
 */
public interface RoleRepository extends JpaRepository<Role, Long> {

    /**
     * Retrieves role by its name.
     *
     * @param name the name of the role
     * @return found role
     */
    Role findByName(String name);
}

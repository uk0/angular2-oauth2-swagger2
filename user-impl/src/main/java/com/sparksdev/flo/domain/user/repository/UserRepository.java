package com.sparksdev.flo.domain.user.repository;

import org.springframework.data.repository.RepositoryDefinition;
import com.sparksdev.flo.dao.BaseRepository;
import com.sparksdev.flo.domain.user.User;

/**
 * @author bengill
 */
@RepositoryDefinition(domainClass = User.class, idClass = String.class)
public interface UserRepository extends BaseRepository<User, String> {

    /**
     * Obtains a user by user name (normally the users email address).
     *
     * @param username
     *         the User name of the user to find normally the users email address
     * @return the user if found otherwise null
     */
    User findByUsername(final String username);
    User findBySessionId(final String sessionId);

}

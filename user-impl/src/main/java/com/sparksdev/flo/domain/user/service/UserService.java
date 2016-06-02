package com.sparksdev.flo.domain.user.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.sparksdev.flo.common.domain.UserId;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.domain.user.repository.UserRepository;

import javax.inject.Inject;

/**
 * @author bengill
 */
@Service
public class UserService {

    @Inject
    private UserRepository userRepository;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);


    /**
     *
     * @param userName
     * @return
     */
    public User getUserByUserName(final String userName) {
        LOG.debug("Loading user [" + userName + "]");

        User user =  userRepository.findByUsername(userName);

        if (user != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Loaded user [" + user.toString() + "]");
            }
        }
        return user;
    }

    /**
     *
     * @param sessionId
     * @return
     */
    public User getUserBySessionId(final String sessionId) {

        User user =  userRepository.findBySessionId(sessionId);

        if (user != null) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Loaded user [" + user.toString() + "]");
            }
        }
        return user;
    }



    /**
     *
     * @param userId
     * @return
     */
    public User getUser(final UserId userId) {
        return userRepository.findOne(userId.getId());
    }


    public User createUser(final User user) {
        return userRepository.save(user);
    }

    public User saveUser(final User user) {
        return userRepository.save(user);
    }
}

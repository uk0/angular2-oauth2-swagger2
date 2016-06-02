package com.sparksdev.flo.domain.user.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sparksdev.flo.common.service.BaseService;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.user.api.user.UserApi;

import javax.inject.Inject;

/**
 * @author bengill
 */
@Service("userApi")
public class UserApiImpl extends BaseService implements UserApi {

    @Inject
    private UserService userService;






    @Override
    public UserDetails loadUserByUsername(final String userName) throws UsernameNotFoundException {
        LOG.info("loadUserByUsername [" + userName + "]");
        final User user = userService.getUserByUserName(userName);
        if (user != null) {
            return user;
        }
        throw new UsernameNotFoundException(userName);
    }

    @Override public void create(final User user) {
        userService.createUser(user);
    }
}

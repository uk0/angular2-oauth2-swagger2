package com.sparksdev.flo.authentication.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.user.api.user.UserApi;

import javax.inject.Inject;

/**
 * @author bengill
 */
@Service
public class UsernamePasswordAuthenticator extends AbstractAuthenticationProvider {

    @Inject
    private UserApi userApi;

    /** {@inheritDoc} */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {
        final User user = (User)userApi.loadUserByUsername((String)
                authentication.getPrincipal());
        return authenticate(authentication, user);
    }

    /** {@inheritDoc} */
    @Override
    public boolean supports(final Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.equals(authentication);
    }
}

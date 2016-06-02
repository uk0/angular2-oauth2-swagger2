package com.sparksdev.flo.authentication.service;

import org.springframework.security.access.intercept.RunAsUserToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import com.sparksdev.flo.domain.user.User;

/**
 * @author bengill
 */
@Service
public class RunAsUserTokenProvider extends AbstractAuthenticationProvider {

    /** {@inheritDoc} */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        User user = getUserFromAuthenticationPrincipal(authentication);

        return bindAuthentication(new RunAsUserToken(
                        Long.toString(System.currentTimeMillis()),
                        user,
                        authentication.getCredentials(),
                        user.getAuthorities(),
                        ((RunAsUserToken) authentication).getOriginalAuthentication())
        );

    }

    /** {@inheritDoc} */
    @Override
    public boolean supports(final Class<?> authentication) {
        return RunAsUserToken.class.equals(authentication);
    }
}
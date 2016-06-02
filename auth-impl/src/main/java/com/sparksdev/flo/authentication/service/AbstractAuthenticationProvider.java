package com.sparksdev.flo.authentication.service;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.user.api.user.UserApi;

import javax.inject.Inject;

/**
 * @author bengill
 */
public abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

    @Inject
    private UserApi userApi;

    /**
     * Sets authentication on Spring Security.
     *
     * @param authentication
     * @return
     */
    public static Authentication bindAuthentication(final Authentication authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    protected User getUserFromAuthenticationPrincipal(final Authentication authentication) {
        final Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        } else if (principal instanceof String) {
            return (User)userApi.loadUserByUsername((String) authentication.getPrincipal());
        }

        throw new BadCredentialsException("Unable to get user from principal");
    }

    protected Authentication authenticate(final Authentication authentication, final User user) {

        if (user != null) {
            if (user.getPassword().equals(authentication.getCredentials())) {
                return bindAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                user,
                                authentication.getCredentials(),
                                user.getAuthorities()));

            }
        }
        throw new BadCredentialsException("Failed to authenticate");
    }

}

package com.sparksdev.flo.authentication.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.sparksdev.flo.common.service.BaseService;
import com.sparksdev.flo.domain.user.User;
import com.sparksdev.flo.user.api.user.UserApi;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

/**
 * @author bengill
 */
@Service
public class AuthenticationService extends BaseService implements AuthenticationProvider {

    @Inject
    private UsernamePasswordAuthenticator usernamePasswordAuthenticator;

    @Inject
    private RunAsUserTokenProvider runAsUserTokenProvider;

    @Inject
    private UserApi userApi;


    /** The collection of registered AuthenticationProviders. */
    private List<AuthenticationProvider> authenticationProviders = new ArrayList<>();

    public User authenticate(final String userName, final String password) {
        try {
            final Authentication authentication = usernamePasswordAuthenticator.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
            return (User) authentication.getPrincipal();
        } catch (UsernameNotFoundException usernameNotFoundException) {
            LOG.info(usernameNotFoundException.getMessage());
            return null;
        }
    }

    /**
     * Performs authentication with the same contract as {@link AuthenticationManager#authenticate(Authentication)} .
     *
     * @param authentication
     *         the authentication request object.
     * @return a fully authenticated object including credentials. May return <code>null</code> if the
     * <code>AuthenticationProvider</code> is unable to support authentication of the passed <code>Authentication</code>
     * object. In such a case, the next <code>AuthenticationProvider</code> that supports the presented
     * <code>Authentication</code> class will be tried.
     * @throws AuthenticationException
     *         if authentication fails.
     */
    @Override
    public Authentication authenticate(final Authentication authentication) throws AuthenticationException {

        try {
            for (AuthenticationProvider authenticationProvider : authenticationProviders) {
                if (authenticationProvider.supports(authentication.getClass())) {
                    return authenticationProvider.authenticate(authentication);
                }
            }

            // The provided authentication is not supported
            throw new BadCredentialsException("Failed authentication");

        } catch (AuthenticationException e) {
            throw e;
        }
    }


    /**
     * Registers the required authentication providers after injection complete
     */
    @PostConstruct
    public void postConstruct() {


        addAuthenticationProvider(usernamePasswordAuthenticator);
        addAuthenticationProvider(runAsUserTokenProvider);
    }

    /**
     * Registers an AuthenticationProvider.
     *
     * @param authenticationProvider
     *         the autenrtication provider to register
     */
    public void addAuthenticationProvider(final AuthenticationProvider authenticationProvider) {
        authenticationProviders.add(authenticationProvider);
    }

    /**
     * Returns <code>true</code> if this <Code>AuthenticationProvider</code> supports the indicated
     * <Code>Authentication</code> object. <p> Returning <code>true</code> does not guarantee an
     * <code>AuthenticationProvider</code> will be able to authenticate the presented instance of the
     * <code>Authentication</code> class. It simply indicates it can support closer evaluation of it. An
     * <code>AuthenticationProvider</code> can still return <code>null</code> from the {@link #authenticate(
     *Authentication)} method to indicate another <code>AuthenticationProvider</code> should be tried. </p> <p>
     * Selection of an <code>AuthenticationProvider</code> capable of performing authentication is conducted at runtime
     * the <code>ProviderManager</code>. </p>
     *
     * @param authentication
     * @return <code>true</code> if the implementation can more closely evaluate the <code>Authentication</code> class
     * presented
     */
    @Override public boolean supports(final Class<?> authentication) {

        for (AuthenticationProvider authenticationProvider : authenticationProviders) {
            if (authenticationProvider.supports(authentication.getClass())) {
                return true;
            }
        }
        return false;
    }


    public void logout() {
        SecurityContextHolder.clearContext();
    }


    public User getLoggedInUser() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication.getPrincipal() instanceof User) {
                return (User) authentication.getPrincipal();
            } else {
                //log.info("Got authentication object but in it was a " + authentication.getPrincipal().getClass() + " class " + authentication.getPrincipal().toString());
                return null;
            }
        }
        return null;
    }
}

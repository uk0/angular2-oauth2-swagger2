package com.sparksdev.flo.authentication.api;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import com.sparksdev.flo.authentication.service.AuthenticationService;
import com.sparksdev.flo.common.service.BaseService;

import javax.inject.Inject;

/**
 * @author bengill
 */
public class AuthApiImpl extends BaseService implements AuthApi {

    @Inject
    private AuthenticationService authenticationService;

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
        return authenticationService.authenticate(authentication);
    }
}

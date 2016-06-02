package com.sparksdev.flo.domain.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author bengill
 */
public class UserLookup {

    public static User getLoggedInUser() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof User) {
            return (User)authentication.getPrincipal();
        }
        return null;
    }

    public static String getLoggedInUserId() {
        User user = getLoggedInUser();

        if (user != null) {
            return user.getId();
        }
        throw new IllegalStateException("No user logged in");
    }
}
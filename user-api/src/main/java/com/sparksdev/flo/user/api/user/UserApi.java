package com.sparksdev.flo.user.api.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.sparksdev.flo.domain.user.User;

/**
 * @author bengill
 */
public interface UserApi extends UserDetailsService {


    void create(User user);

}


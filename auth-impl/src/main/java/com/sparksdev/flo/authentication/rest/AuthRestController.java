package com.sparksdev.flo.authentication.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sparksdev.flo.authentication.api.AuthApi;
import com.sparksdev.flo.common.domain.ValidationUtils;
import com.sparksdev.flo.common.service.BaseService;

import javax.inject.Inject;

/**
 * @author bengill
 */
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthRestController extends BaseService {

    @Inject
    AuthApi authApi;

    @RequestMapping(method = RequestMethod.GET)
    public void login(@RequestParam("username") final String username, @RequestParam("password") final String password) {
        ValidationUtils.checkNotNull(username, "username");
        ValidationUtils.checkNotNull(password, "password");
        //return response.getLoggedInUser();
    }
}


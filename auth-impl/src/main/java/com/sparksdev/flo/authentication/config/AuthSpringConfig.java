package com.sparksdev.flo.authentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.sparksdev.flo.authentication.api.AuthApi;
import com.sparksdev.flo.authentication.api.AuthApiImpl;
import com.sparksdev.flo.authentication.service.AuthenticationService;
import com.sparksdev.flo.authentication.service.RunAsUserTokenProvider;
import com.sparksdev.flo.authentication.service.UsernamePasswordAuthenticator;

/**
 * @author bengill
 */
@Configuration
public class AuthSpringConfig {

    @Primary
    @Bean
    public AuthApi authApi() { return new AuthApiImpl(); }

    @Primary
    @Bean
    public AuthenticationService authenticationService() { return new AuthenticationService(); }

    @Primary
    @Bean
    public UsernamePasswordAuthenticator usernamePasswordAuthenticator() { return new UsernamePasswordAuthenticator(); }

    @Primary
    @Bean
    public RunAsUserTokenProvider runAsUserTokenProvider() { return new RunAsUserTokenProvider(); }
}
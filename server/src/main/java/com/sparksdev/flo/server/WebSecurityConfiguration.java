package com.sparksdev.flo.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import com.sparksdev.flo.authentication.api.AuthApi;
import com.sparksdev.flo.user.api.user.UserApi;

import javax.annotation.Resource;

/**
 * @author bengill
 */

@Configuration
@EnableOAuth2Client
@EnableAuthorizationServer
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    private AuthApi authApi;

    @Autowired
    private UserApi userApi;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userApi);
    }

    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return authApi;
    }


    @Override
    public void configure(WebSecurity webSecurity) throws Exception {
        webSecurity
                .ignoring()
                .antMatchers("/swagger-ui**", "/swagger-ui/**", "/swagger-editor**", "/webjars/**", "/configuration/**", "/swagger-resources/**", "/v2/**");


    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {


        http.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class);


        http
                .requestMatcher(new AntPathRequestMatcher("/oauth/**"))
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.authorizeRequests()
                .antMatchers("/rest/**").anonymous()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);


        //http.formLogin().permitAll().and().authorizeRequests().anyRequest().authenticated().and().csrf().disable();//.and().userDetailsService(yourCustomerUserDetailsService);

        // permitAll lets it through...
        http.authorizeRequests().anyRequest().permitAll();
    }

   /* @EnableAuthorizationServer
    protected static class OAuth2Config extends AuthorizationServerConfigurerAdapter {

        @Resource
        private AuthApi authApi;

        @Override
        public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
            oauthServer.allowFormAuthenticationForClients();
        }


        @Override // [2]
        public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
            endpoints.authenticationManager(authApi);
        }

        @Override
        public void configure(ClientDetailsServiceConfigurer clients) throws Exception {


            clients.inMemory()
                    .withClient("foo")
                    //.resourceIds(xxx)
                    .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
                    .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
                    .scopes("read", "write", "trust", "update")
                    .accessTokenValiditySeconds(1200) // 20 min
                    .refreshTokenValiditySeconds(60) // 1 min
                    .secret("bar");

        }
    }*/
}

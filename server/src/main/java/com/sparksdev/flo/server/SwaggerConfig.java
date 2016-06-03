package com.sparksdev.flo.server;

import static com.google.common.collect.Lists.newArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import java.util.Arrays;
import java.util.List;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author bengill
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    public static final String securitySchemaOAuth2 = "oauth2schema";
    public static final String authorizationScopeGlobal = "global";
    public static final String authorizationScopeGlobalDesc ="accessEverything";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()

                .apis(RequestHandlerSelectors.any())
                .paths(internalPaths())



                // This will apply the security scheme to all paths
                .build()
                .securitySchemes(Arrays.asList(securitySchema()))
                .securityContexts(Arrays.asList(securityContext()));

    }

    private Predicate<String> internalPaths() {
        return PathSelectors.regex("/users.*");
    }

    private OAuth securitySchema() {
        AuthorizationScope authorizationScope = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobal);

        TokenRequestEndpoint tokenRequestEndpoint = new TokenRequestEndpoint("/oauth/authorize", "foo", "bar");
        TokenEndpoint tokenEndpoint = new TokenEndpoint("/oauth/token", "access_code");

        GrantType grantType = new AuthorizationCodeGrant(tokenRequestEndpoint, tokenEndpoint);

        // new GrantType("read:user")
        return new OAuth(securitySchemaOAuth2, newArrayList(authorizationScope), newArrayList(grantType));
    }


    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .forPaths(internalPaths())
                .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope(authorizationScopeGlobal, authorizationScopeGlobalDesc);
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference(securitySchemaOAuth2, authorizationScopes));
    }


}
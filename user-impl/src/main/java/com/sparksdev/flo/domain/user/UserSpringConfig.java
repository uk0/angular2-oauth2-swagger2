package com.sparksdev.flo.domain.user;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import com.sparksdev.flo.domain.user.service.UserApiImpl;
import com.sparksdev.flo.domain.user.service.UserService;
import com.sparksdev.flo.user.api.user.UserApi;

@Configuration
@EnableMongoRepositories(basePackages = "com.sparksdev.flo.domain.user.repository")
public class UserSpringConfig {



    @Primary
    @Bean
    public UserService userService() {
        return new UserService();
    }


    @Primary
    @Bean
    public UserApi userApi() {
        return new UserApiImpl();
    }



}

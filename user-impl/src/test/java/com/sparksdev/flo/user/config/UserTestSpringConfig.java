package com.sparksdev.flo.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import com.sparksdev.flo.authentication.config.AuthSpringConfig;
import com.sparksdev.flo.common.config.CommonSpringConfig;
import com.sparksdev.flo.dao.SpringMongoConfig;
import com.sparksdev.flo.domain.user.UserSpringConfig;

/**
 * @author bengill
 */

@Configuration
@Import( value = { UserSpringConfig.class,
        CommonSpringConfig.class,
        AuthSpringConfig.class,
        SpringMongoConfig.class } )
public class UserTestSpringConfig {
}

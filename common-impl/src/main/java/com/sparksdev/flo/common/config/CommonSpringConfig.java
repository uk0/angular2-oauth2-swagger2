package com.sparksdev.flo.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import com.sparksdev.flo.common.spring.FloContextAware;

/**
 * @author bengill
 */
@Configuration
public class CommonSpringConfig {

    @Primary
    @Bean
    public FloContextAware floContextAware() { return new FloContextAware(); }
}


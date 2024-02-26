package com.charlie.parking;

import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.*;

@Configuration
@EnableJpaRepositories(basePackages = "com.charlie.parking.repository")
public class JpaConfig {
    // Additional JPA configuration if needed
}

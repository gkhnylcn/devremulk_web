package com.devremulk.web.config;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.repository.UserRepository;
import com.devremulk.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DataInitializer.class);

    @Bean
    public CommandLineRunner seedUsers(UserRepository userRepository, UserService userService) {
        return args -> {
            if (userRepository.count() == 0) {
                UserCreateRequest request = new UserCreateRequest();
                request.setUsername("admin");
                request.setPassword("admin123");
                request.setRoles("ROLE_ADMIN");
                userService.create(request);
                logger.info("Default admin user created");
            }
        };
    }
}

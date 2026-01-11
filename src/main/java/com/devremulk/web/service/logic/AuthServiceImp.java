package com.devremulk.web.service.logic;

import com.devremulk.web.dto.AuthLoginRequest;
import com.devremulk.web.dto.AuthLoginResponse;
import com.devremulk.web.service.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImp implements AuthService {
    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImp.class);

    private final AuthenticationManager authenticationManager;

    public AuthServiceImp(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public AuthLoginResponse login(AuthLoginRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );
        logger.info("User {} logged in", authentication.getName());
        return new AuthLoginResponse("Login successful");
    }
}

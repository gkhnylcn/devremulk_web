package com.devremulk.web.service;

import com.devremulk.web.dto.AuthLoginRequest;
import com.devremulk.web.dto.AuthLoginResponse;

public interface AuthService {
    AuthLoginResponse login(AuthLoginRequest request);
}

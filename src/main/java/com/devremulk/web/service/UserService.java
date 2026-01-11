package com.devremulk.web.service;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.dto.UserDto;
import java.util.List;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    UserDto create(UserCreateRequest request);

    UserDto get(Long id);

    List<UserDto> list();
}

package com.devremulk.web.rest;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.dto.UserDto;
import java.util.List;

public interface UserRestService {
    UserDto create(UserCreateRequest request);

    UserDto get(Long id);

    List<UserDto> list();
}

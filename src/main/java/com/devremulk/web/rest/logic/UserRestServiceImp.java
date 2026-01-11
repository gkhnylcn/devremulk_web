package com.devremulk.web.rest.logic;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.dto.UserDto;
import com.devremulk.web.rest.UserRestService;
import com.devremulk.web.service.UserService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserRestServiceImp implements UserRestService {
    private static final Logger logger = LoggerFactory.getLogger(UserRestServiceImp.class);

    private final UserService userService;

    public UserRestServiceImp(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDto create(UserCreateRequest request) {
        logger.debug("Rest service create user request received");
        return userService.create(request);
    }

    @Override
    public UserDto get(Long id) {
        logger.debug("Rest service get user request received for id {}", id);
        return userService.get(id);
    }

    @Override
    public List<UserDto> list() {
        logger.debug("Rest service list user request received");
        return userService.list();
    }
}

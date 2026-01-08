package com.devremulk.web.rest;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.dto.UserDto;
import jakarta.validation.Valid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
public class UserRestController {
    private static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

    private final UserRestService userRestService;

    public UserRestController(UserRestService userRestService) {
        this.userRestService = userRestService;
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody UserCreateRequest request) {
        logger.info("User create request received");
        return ResponseEntity.status(HttpStatus.CREATED).body(userRestService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> get(@PathVariable Long id) {
        logger.info("User get request received for id {}", id);
        return ResponseEntity.ok(userRestService.get(id));
    }

    @GetMapping
    public ResponseEntity<List<UserDto>> list() {
        logger.info("User list request received");
        return ResponseEntity.ok(userRestService.list());
    }
}

package com.devremulk.web.service.logic;

import com.devremulk.web.dto.UserCreateRequest;
import com.devremulk.web.dto.UserDto;
import com.devremulk.web.entity.UserEntity;
import com.devremulk.web.exception.ResourceNotFoundException;
import com.devremulk.web.repository.UserRepository;
import com.devremulk.web.service.UserService;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImp implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImp.class);

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto create(UserCreateRequest request) {
        UserEntity entity = new UserEntity();
        entity.setUsername(request.getUsername());
        entity.setPassword(passwordEncoder.encode(request.getPassword()));
        entity.setRoles(request.getRoles());
        entity.setEnabled(true);
        UserEntity saved = userRepository.save(entity);
        logger.info("User created with id {}", saved.getId());
        return toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto get(Long id) {
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));
        return toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> list() {
        return userRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) {
        UserEntity entity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        List<GrantedAuthority> authorities = Arrays.stream(entity.getRoles().split(","))
                .map(String::trim)
                .filter(role -> !role.isBlank())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return User.builder()
                .username(entity.getUsername())
                .password(entity.getPassword())
                .authorities(authorities)
                .disabled(Boolean.FALSE.equals(entity.getEnabled()))
                .build();
    }

    private UserDto toDto(UserEntity entity) {
        UserDto dto = new UserDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setRoles(entity.getRoles());
        dto.setEnabled(entity.getEnabled());
        dto.setCreatedUser(entity.getCreatedUser());
        dto.setCreatedDatetime(entity.getCreatedDatetime());
        dto.setUpdateUser(entity.getUpdateUser());
        dto.setUpdateDatetime(entity.getUpdateDatetime());
        dto.setRemoveUser(entity.getRemoveUser());
        dto.setRemoveDatetime(entity.getRemoveDatetime());
        dto.setDeleted(entity.getDeleted());
        dto.setVersion(entity.getVersion());
        dto.setUuid(entity.getUuid());
        return dto;
    }
}

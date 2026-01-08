package com.devremulk.microservice.property.controller;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.devremulk.microservice.property.dto.PropertyResponse;
import com.devremulk.microservice.property.rest.PropertyRestService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/properties")
public class PropertyController {

    private final PropertyRestService propertyRestService;

    public PropertyController(PropertyRestService propertyRestService) {
        this.propertyRestService = propertyRestService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PropertyResponse create(@Valid @RequestBody PropertyRequest request) {
        return propertyRestService.create(request);
    }

    @GetMapping
    public List<PropertyResponse> list(@RequestParam Optional<String> city) {
        return propertyRestService.list(city);
    }

    @PutMapping("/{id}")
    public PropertyResponse update(@PathVariable Long id, @Valid @RequestBody PropertyRequest request) {
        return propertyRestService.update(id, request);
    }
}

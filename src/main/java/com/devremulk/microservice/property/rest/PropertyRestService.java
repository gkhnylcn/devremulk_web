package com.devremulk.microservice.property.rest;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.devremulk.microservice.property.dto.PropertyResponse;
import java.util.List;
import java.util.Optional;

public interface PropertyRestService {
    PropertyResponse create(PropertyRequest request);

    List<PropertyResponse> list(Optional<String> city);

    PropertyResponse update(Long id, PropertyRequest request);
}

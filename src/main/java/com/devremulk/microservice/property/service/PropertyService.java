package com.devremulk.microservice.property.service;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.devremulk.microservice.property.entity.Property;
import java.util.List;
import java.util.Optional;

public interface PropertyService {
    Property create(PropertyRequest request);

    List<Property> findAll(Optional<String> city);

    Property update(Long id, PropertyRequest request);
}

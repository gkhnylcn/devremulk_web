package com.devremulk.microservice.property.rest;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.devremulk.microservice.property.dto.PropertyResponse;
import com.devremulk.microservice.property.entity.Property;
import com.devremulk.microservice.property.service.PropertyService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PropertyRestServiceImp implements PropertyRestService {

    private final PropertyService propertyService;

    public PropertyRestServiceImp(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @Override
    public PropertyResponse create(PropertyRequest request) {
        Property property = propertyService.create(request);
        return PropertyResponse.from(property);
    }

    @Override
    public List<PropertyResponse> list(Optional<String> city) {
        return propertyService.findAll(city).stream()
                .map(PropertyResponse::from)
                .toList();
    }

    @Override
    public PropertyResponse update(Long id, PropertyRequest request) {
        return PropertyResponse.from(propertyService.update(id, request));
    }
}

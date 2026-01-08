package com.devremulk.microservice.property.dto;

import com.devremulk.microservice.property.entity.Property;

public record PropertyResponse(
        Long id,
        String title,
        String city,
        String status
) {
    public static PropertyResponse from(Property property) {
        return new PropertyResponse(
                property.getId(),
                property.getTitle(),
                property.getCity(),
                property.getStatus()
        );
    }
}

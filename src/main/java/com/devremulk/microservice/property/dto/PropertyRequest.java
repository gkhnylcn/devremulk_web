package com.devremulk.microservice.property.dto;

import jakarta.validation.constraints.NotBlank;

public record PropertyRequest(
        @NotBlank String title,
        @NotBlank String city,
        @NotBlank String status
) {
}

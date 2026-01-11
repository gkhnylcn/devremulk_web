package com.devremulk.web.dto;

import com.devremulk.web.base.BaseDto;
import jakarta.validation.constraints.NotBlank;

public class SampleDto extends BaseDto {
    private Long id;

    @NotBlank
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.devremulk.web.service;

import com.devremulk.web.dto.SampleDto;
import java.util.List;

public interface SampleService {
    SampleDto create(SampleDto dto);

    SampleDto update(Long id, SampleDto dto);

    void delete(Long id);

    SampleDto get(Long id);

    List<SampleDto> list();
}

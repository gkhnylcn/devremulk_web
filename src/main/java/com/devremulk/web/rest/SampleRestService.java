package com.devremulk.web.rest;

import com.devremulk.web.dto.SampleDto;
import java.util.List;

public interface SampleRestService {
    SampleDto create(SampleDto dto);

    SampleDto update(Long id, SampleDto dto);

    void delete(Long id);

    SampleDto get(Long id);

    List<SampleDto> list();
}

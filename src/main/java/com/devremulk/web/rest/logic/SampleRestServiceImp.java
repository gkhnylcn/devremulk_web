package com.devremulk.web.rest.logic;

import com.devremulk.web.dto.SampleDto;
import com.devremulk.web.rest.SampleRestService;
import com.devremulk.web.service.SampleService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SampleRestServiceImp implements SampleRestService {
    private static final Logger logger = LoggerFactory.getLogger(SampleRestServiceImp.class);

    private final SampleService sampleService;

    public SampleRestServiceImp(SampleService sampleService) {
        this.sampleService = sampleService;
    }

    @Override
    public SampleDto create(SampleDto dto) {
        logger.debug("Rest service create request received");
        return sampleService.create(dto);
    }

    @Override
    public SampleDto update(Long id, SampleDto dto) {
        logger.debug("Rest service update request received for id {}", id);
        return sampleService.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        logger.debug("Rest service delete request received for id {}", id);
        sampleService.delete(id);
    }

    @Override
    public SampleDto get(Long id) {
        logger.debug("Rest service get request received for id {}", id);
        return sampleService.get(id);
    }

    @Override
    public List<SampleDto> list() {
        logger.debug("Rest service list request received");
        return sampleService.list();
    }
}

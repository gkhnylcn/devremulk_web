package com.devremulk.web.service.logic;

import com.devremulk.web.dto.SampleDto;
import com.devremulk.web.entity.SampleEntity;
import com.devremulk.web.exception.ResourceNotFoundException;
import com.devremulk.web.repository.SampleRepository;
import com.devremulk.web.service.SampleService;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SampleServiceImp implements SampleService {
    private static final Logger logger = LoggerFactory.getLogger(SampleServiceImp.class);

    private final SampleRepository sampleRepository;
    private final JPAQueryFactory queryFactory;

    public SampleServiceImp(SampleRepository sampleRepository, JPAQueryFactory queryFactory) {
        this.sampleRepository = sampleRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public SampleDto create(SampleDto dto) {
        SampleEntity entity = new SampleEntity();
        entity.setName(dto.getName());
        SampleEntity saved = sampleRepository.save(entity);
        logger.info("Sample created with id {}", saved.getId());
        return toDto(saved);
    }

    @Override
    public SampleDto update(Long id, SampleDto dto) {
        SampleEntity entity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found"));
        entity.setName(dto.getName());
        SampleEntity saved = sampleRepository.save(entity);
        logger.info("Sample updated with id {}", saved.getId());
        return toDto(saved);
    }

    @Override
    public void delete(Long id) {
        SampleEntity entity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found"));
        entity.setDeleted(true);
        sampleRepository.save(entity);
        logger.info("Sample soft deleted with id {}", id);
    }

    @Override
    @Transactional(readOnly = true)
    public SampleDto get(Long id) {
        SampleEntity entity = sampleRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sample not found"));
        return toDto(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SampleDto> list() {
        PathBuilder<SampleEntity> sample = new PathBuilder<>(SampleEntity.class, "sample");
        return queryFactory.selectFrom(sample)
                .fetch()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private SampleDto toDto(SampleEntity entity) {
        SampleDto dto = new SampleDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setCreatedUser(entity.getCreatedUser());
        dto.setCreatedDatetime(entity.getCreatedDatetime());
        dto.setUpdateUser(entity.getUpdateUser());
        dto.setUpdateDatetime(entity.getUpdateDatetime());
        dto.setRemoveUser(entity.getRemoveUser());
        dto.setRemoveDatetime(entity.getRemoveDatetime());
        dto.setDeleted(entity.getDeleted());
        dto.setVersion(entity.getVersion());
        dto.setUuid(entity.getUuid());
        return dto;
    }
}

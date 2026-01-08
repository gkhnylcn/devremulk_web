package com.devremulk.web.repository;

import com.devremulk.web.entity.SampleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface SampleRepository extends JpaRepository<SampleEntity, Long>, QuerydslPredicateExecutor<SampleEntity> {
}

package com.devremulk.microservice.property;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PropertyRepository extends JpaRepository<Property, Long>, QuerydslPredicateExecutor<Property> {
}

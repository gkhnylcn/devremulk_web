package com.devremulk.microservice.property.service.impl;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.devremulk.microservice.property.entity.Property;
import com.devremulk.microservice.property.entity.QProperty;
import com.devremulk.microservice.property.repository.PropertyRepository;
import com.devremulk.microservice.property.service.PropertyService;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyServiceImp implements PropertyService {

    private final PropertyRepository propertyRepository;
    private final JPAQueryFactory queryFactory;

    public PropertyServiceImp(PropertyRepository propertyRepository, JPAQueryFactory queryFactory) {
        this.propertyRepository = propertyRepository;
        this.queryFactory = queryFactory;
    }

    @Override
    public Property create(PropertyRequest request) {
        Property property = new Property(request.title(), request.city(), request.status());
        return propertyRepository.save(property);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Property> findAll(Optional<String> city) {
        QProperty property = QProperty.property;
        BooleanExpression predicate = city
                .filter(value -> !value.isBlank())
                .map(property.city::equalsIgnoreCase)
                .orElse(null);

        return queryFactory.selectFrom(property)
                .where(predicate)
                .fetch();
    }

    @Override
    public Property update(Long id, PropertyRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        property.update(request.title(), request.city(), request.status());
        return property;
    }
}

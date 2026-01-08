package com.devremulk.microservice.property;

import com.devremulk.microservice.property.dto.PropertyRequest;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final JPAQueryFactory queryFactory;

    public PropertyService(PropertyRepository propertyRepository, JPAQueryFactory queryFactory) {
        this.propertyRepository = propertyRepository;
        this.queryFactory = queryFactory;
    }

    public Property create(PropertyRequest request) {
        Property property = new Property(request.title(), request.city(), request.status());
        return propertyRepository.save(property);
    }

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

    public Property update(Long id, PropertyRequest request) {
        Property property = propertyRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Property not found"));
        property.update(request.title(), request.city(), request.status());
        return property;
    }
}

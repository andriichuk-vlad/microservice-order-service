package com.example_microservice.order_service.dto;

import java.math.BigDecimal;

public record ProductClientDto(
        Long id,
        String name,
        BigDecimal price,
        Integer stockQuantity
) {
}

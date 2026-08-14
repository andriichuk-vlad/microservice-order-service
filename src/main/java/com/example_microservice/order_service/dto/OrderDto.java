package com.example_microservice.order_service.dto;

import java.math.BigDecimal;

public record OrderDto (
        Long id,
        Long productId,
        String status,
        BigDecimal totalPrice,
        String name,
        Integer quantity) {
}

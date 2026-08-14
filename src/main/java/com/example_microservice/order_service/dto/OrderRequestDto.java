package com.example_microservice.order_service.dto;

public record OrderRequestDto (
        Long productId,
        Integer quantity
) {
}

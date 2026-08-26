package com.example_microservice.order_service.client;

import com.example_microservice.order_service.dto.ProductClientDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class ProductServiceFallback implements ProductClient {
    private final Throwable cause;

    public ProductServiceFallback(Throwable cause) {
        this.cause = cause;
    }

    @Override
    public ProductClientDto getProductById(Long id) {
        throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, cause.getMessage());
    }

    @Override
    public void decreaseQuantity(Long id, Integer quantity) {
        throw new RuntimeException(cause);
    }
}

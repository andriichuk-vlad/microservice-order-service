package com.example_microservice.order_service.client;

import com.example_microservice.order_service.dto.ProductClientDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(value = "products", url = "http://localhost:8081", fallbackFactory = ProductClientFallbackFactory.class)
public interface ProductClient {

    @GetMapping("/products/{productId}")
    ProductClientDto getProductById(@PathVariable("productId") Long id);

    @PutMapping("/products/{productId}/subtract")
    void decreaseQuantity(@PathVariable("productId") Long id, @RequestParam Integer quantity);
}

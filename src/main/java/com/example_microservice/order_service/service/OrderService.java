package com.example_microservice.order_service.service;

import com.example_microservice.order_service.dto.OrderDto;
import com.example_microservice.order_service.dto.OrderRequestDto;

public interface OrderService {
    OrderDto createOrder(OrderRequestDto orderRequest);
}

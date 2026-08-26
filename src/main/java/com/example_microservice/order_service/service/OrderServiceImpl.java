package com.example_microservice.order_service.service;

import com.example_microservice.order_service.client.ProductClient;
import com.example_microservice.order_service.dto.OrderDto;
import com.example_microservice.order_service.dto.OrderRequestDto;
import com.example_microservice.order_service.dto.ProductClientDto;
import com.example_microservice.order_service.exception.InsufficientQuantityException;
import com.example_microservice.order_service.mapper.OrderMapper;
import com.example_microservice.order_service.model.Order;
import com.example_microservice.order_service.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Transactional
public class OrderServiceImpl implements OrderService{
    private final ProductClient productClient;
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderServiceImpl(ProductClient productClient, OrderRepository orderRepository, OrderMapper orderMapper) {
        this.productClient = productClient;
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    @Override
    public OrderDto createOrder(OrderRequestDto orderRequest) {
        ProductClientDto productById = productClient.getProductById(orderRequest.productId());
        if (orderRequest.quantity() > productById.stockQuantity()) {
            throw new InsufficientQuantityException("Not enough product in stock. " +
                    "Available: " + productById.stockQuantity()
                    + ", requested: " + orderRequest.quantity());
        }
        Order order = new Order();
        BigDecimal sum = productById.price().multiply(BigDecimal.valueOf(orderRequest.quantity()));
        order.setTotalPrice(sum);
        order.setProductId(productById.id());
        order.setStatus(Order.Status.PENDING);
        orderRepository.save(order);
        productClient.decreaseQuantity(orderRequest.productId(), orderRequest.quantity());
        return orderMapper.toDto(order, productById, orderRequest.quantity());
    }
}

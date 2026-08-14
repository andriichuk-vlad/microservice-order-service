package com.example_microservice.order_service.mapper;

import com.example_microservice.order_service.config.MapperConfig;
import com.example_microservice.order_service.dto.OrderDto;
import com.example_microservice.order_service.dto.OrderRequestDto;
import com.example_microservice.order_service.dto.ProductClientDto;
import com.example_microservice.order_service.model.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(config = MapperConfig.class)
public interface OrderMapper {

//    Order toModel(OrderRequestDto orderRequest);

    @Mapping(target = "id", source = "order.id")
    @Mapping(target = "status", source = "order.status")
    @Mapping(target = "totalPrice", source = "order.totalPrice")
    @Mapping(target = "productId", source = "productClientDto.id")
    @Mapping(target = "name", source = "productClientDto.name")
    @Mapping(target = "quantity", source = "requestedQuantity")
    OrderDto toDto(Order order, ProductClientDto productClientDto, Integer requestedQuantity);
}

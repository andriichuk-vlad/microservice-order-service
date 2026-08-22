package com.example_microservice.order_service.exception;

import lombok.Getter;
import java.util.Map;

@Getter
public class ForwardedFeignException extends RuntimeException{
    private final int status;
    private final Map<String, Object> errorDetails;

    public ForwardedFeignException(int status, Map<String, Object> errorDetails) {
        this.status = status;
        this.errorDetails = errorDetails;
    }
}

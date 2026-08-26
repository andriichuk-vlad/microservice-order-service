package com.example_microservice.order_service.config;

import com.example_microservice.order_service.exception.ForwardedFeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.stereotype.Component;
import tools.jackson.core.type.TypeReference;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Component
public class CustomErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try (InputStream inputStream = response.body().asInputStream()) {
            Map<String, Object> details = objectMapper.readValue(inputStream, new TypeReference<>() {});
            return new ForwardedFeignException(response.status(), details);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

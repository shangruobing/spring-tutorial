package com.infoweaver.springtutorial.common;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author Ruobing Shang 2022-09-01 17:11
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    private ObjectMapper objectMapper;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public boolean supports(@NotNull MethodParameter returnType, @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    @SneakyThrows(JsonProcessingException.class)
    public Object beforeBodyWrite(Object body, @NotNull MethodParameter returnType, @NotNull MediaType selectedContentType, @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType, @NotNull ServerHttpRequest request, @NotNull ServerHttpResponse response) {
        if (body instanceof String) {
            return objectMapper.writeValueAsString(Response.success(body));
        }
        if (body instanceof Response) {
            return body;
        }
        return Response.success(body);
    }

}
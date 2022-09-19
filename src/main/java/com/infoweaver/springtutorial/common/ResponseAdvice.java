package com.infoweaver.springtutorial.common;

import org.jetbrains.annotations.NotNull;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-01 17:11
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final String MYBATIS_PLUS_SUCCESS_STATUS = "1";

    @Override
    public boolean supports(@NotNull MethodParameter returnType,
                            @NotNull Class<? extends HttpMessageConverter<?>> converterType) {
        /**
         * Avoid intercepting Swagger, making it unavailable.
         */
        System.out.println("啊啊啊" + returnType.getDeclaringClass().getName());
        if (returnType.getDeclaringClass().getName().contains("knife4j")) {
            return false;
        }
        if (returnType.getDeclaringClass().getName().contains("springfox")) {
            return false;
        }
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  @NotNull MethodParameter returnType,
                                  @NotNull MediaType selectedContentType,
                                  @NotNull Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  @NotNull ServerHttpRequest request,
                                  @NotNull ServerHttpResponse response) {
        if (body instanceof String) {
            return Map.of("message", body);
        }

        if (MYBATIS_PLUS_SUCCESS_STATUS.equals(body.toString())) {
            return Map.of("message", HttpStatus.OK.getReasonPhrase());
        }
        return body;
    }

}
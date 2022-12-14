package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.util.StringUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.util.List;
import java.util.Map;

/**
 * @author Ruobing Shang 2022-09-01 17:11
 */
@RestControllerAdvice
public class ResponseAdvice implements ResponseBodyAdvice<Object> {
    private static final String MYBATIS_PLUS_SUCCESS_STATUS = "1";

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        /**
         * Avoid intercepting Swagger and Knife4j, making them unavailable.
         */
        List<String> excludeList = List.of("springfox", "knife4j");
        return StringUtils.isNotContains(returnType.getDeclaringClass().getName(), excludeList);
    }

    @Override
    public Object beforeBodyWrite(@Nullable Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body == null) {
            return Map.of("message", "The server returned nothing.");
        }

        if (body instanceof String) {
            return Map.of("message", body);
        }

        if (MYBATIS_PLUS_SUCCESS_STATUS.equals(body.toString())) {
            return Map.of("message", HttpStatus.OK.getReasonPhrase());
        }

        if (body instanceof Boolean) {
            String message = (Boolean) body ? HttpStatus.OK.getReasonPhrase() : HttpStatus.BAD_REQUEST.getReasonPhrase();
            return Map.of("message", message);
        }

        return body;
    }

}
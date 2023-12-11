package com.infoweaver.springtutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoweaver.springtutorial.constant.Status;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @author Ruobing Shang 2023-10-24 08:31
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {
    /**
     * 403权限不足异常捕获
     *
     * @param request
     * @param response
     * @param accessDeniedException
     * @throws IOException
     */
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException {
        response.setStatus(Status.HTTP_403_FORBIDDEN.getCode());
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), Map.of("message", accessDeniedException.getMessage()));
    }
}

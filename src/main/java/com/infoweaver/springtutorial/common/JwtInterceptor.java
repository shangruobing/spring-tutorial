package com.infoweaver.springtutorial.common;

import com.infoweaver.springtutorial.util.JwtAuthentication;
import org.jetbrains.annotations.NotNull;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Ruobing Shang 2022-09-04 20:50
 */
public class JwtInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        try {
            JwtAuthentication.parseAuth(token);
            return true;

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

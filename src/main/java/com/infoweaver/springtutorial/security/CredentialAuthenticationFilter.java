package com.infoweaver.springtutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoweaver.springtutorial.constant.Status;
import com.infoweaver.springtutorial.util.KeyUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.security.sasl.AuthenticationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

/**
 * @author Ruobing Shang 2023-12-6 21:32
 */
@Slf4j
@Component
public class CredentialAuthenticationFilter extends OncePerRequestFilter {
    private final static String CREDENTIAL_HEADER = "Credential";
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public CredentialAuthenticationFilter(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader(CREDENTIAL_HEADER);
        /**
         * 如果未携带SECRET，则放行，交给其他过滤器处理
         */
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        /**
         * 携带CREDENTIAL_HEADER开始校验token
         */
        try {
            String credential = KeyUtils.encryption("infoweaver");
            if (!credential.equals(token)) {
                throw new AuthenticationException("身份校验失败，证书不一致");
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("密钥登录", token, new ArrayList<>());
            /**
             * 明细设置为密钥登录，方便后续根据上下文获取用户id
             */
            UserDetails loginUser = userDetailsService.loadUserById("1");
            authenticationToken.setDetails(loginUser);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            filterChain.doFilter(request, response);
        } catch (AuthenticationException ex) {
            response.setStatus(Status.HTTP_401_UNAUTHORIZED.getCode());
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(response.getOutputStream(), Map.of("message", ex.getMessage()));
        }
    }
}
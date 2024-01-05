package com.infoweaver.springtutorial.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.infoweaver.springtutorial.constant.Status;
import com.infoweaver.springtutorial.util.DateTimeUtils;
import com.infoweaver.springtutorial.util.JwtAuthenticationUtils;
import com.infoweaver.springtutorial.util.RedisUtils;
import io.jsonwebtoken.Claims;
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
import java.time.Duration;
import java.util.Map;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-10-24 08:32
 */
@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final static String AUTHORIZATION_HEADER = "Authorization";
    private final UserDetailsServiceImpl userDetailsService;
    private final RedisUtils redisUtils;

    @Autowired
    public JwtAuthenticationFilter(UserDetailsServiceImpl userDetailsService, RedisUtils redisUtils) {
        this.userDetailsService = userDetailsService;
        this.redisUtils = redisUtils;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        /**
         * login接口带Authorization的Header会导致解析异常，所以直接放行
         */
        if ("/user/login".equals(request.getServletPath())) {
            filterChain.doFilter(request, response);
            return;
        }
        String token = request.getHeader(AUTHORIZATION_HEADER);
        /**
         * 如果未携带Authorization，则放行，交给其他过滤器处理
         */
        if (!StringUtils.hasText(token)) {
            filterChain.doFilter(request, response);
            return;
        }
        /**
         * 携带Authorization开始校验token
         */
        try {
            Claims secretClaims = JwtAuthenticationUtils.parseAuth(token);
            String currentUserId = secretClaims.getId();
            UserDetails loginUser = userDetailsService.loadUserById(currentUserId);
            if (!loginUser.isEnabled()) {
                throw new AuthenticationException("该用户已被禁用，请联系管理员");
            }
            String cacheKey = "JWT_LOGIN_USER_" + currentUserId;
            Optional<Object> tokenInCache = Optional.ofNullable(redisUtils.get(cacheKey));
            if (tokenInCache.isPresent()) {
                String existToken = tokenInCache.get().toString();
                /**
                 * 如果redis与请求头的token不一致，说明已经在其他地方重新登录过
                 */
                if (!existToken.equals(token)) {
                    Claims existSecretClaims = JwtAuthenticationUtils.parseAuth(existToken);
                    /**
                     * 如果redis的token签发日期在请求头的token签发日期之后，说明已经在其他地方重新登录过
                     */
                    if (existSecretClaims.getIssuedAt().after(secretClaims.getIssuedAt())) {
                        throw new AuthenticationException("该用户已于" + DateTimeUtils.formatDate(existSecretClaims.getIssuedAt()) + "在其他设备登录，请重新登录");
                    }
                    /**
                     * 如果redis的token签发日期在请求头的token签发日期之前，说明redis的token已经过期，需要覆盖
                     */
                    else {
                        redisUtils.set(cacheKey, token, Duration.ofDays(7));
                    }
                }
            } else {
                redisUtils.set(cacheKey, token, Duration.ofDays(7));
            }
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser.getUsername(), loginUser.getPassword(), loginUser.getAuthorities());
            /**
             * 明细设置为用户的id，方便后续根据上下文获取用户id
             */
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
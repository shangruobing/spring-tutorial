package com.infoweaver.springtutorial.aspect;


import com.infoweaver.springtutorial.annotation.Debounce;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

/**
 * @author Ruobing Shang 2023-11-07 17:31
 */
@Aspect
@Component
public class DebounceAspect {
    private final com.infoweaver.springtutorial.util.RedisUtils redisUtils;

    @Autowired
    public DebounceAspect(com.infoweaver.springtutorial.util.RedisUtils redisUtils) {
        this.redisUtils = redisUtils;
    }

    @Around("@annotation(debounce)")
    public Object debounce(ProceedingJoinPoint proceedingJoinPoint, Debounce debounce) throws Throwable {
        String method = proceedingJoinPoint.getSignature().getName();
        Integer currentUserId = com.infoweaver.springtutorial.security.SecurityUtils.getCurrentUserId();
        String cacheKey = "DEBOUNCE_USER_" + currentUserId +"_METHOD_"+ method;
        if (Optional.ofNullable(redisUtils.get(cacheKey)).isPresent()) {
            throw new RuntimeException("访问频率过高，请等待" + debounce.value() / 1000 + "秒后再访问");
        } else {
            redisUtils.set(cacheKey, cacheKey, Duration.ofMillis(debounce.value()));
            return proceedingJoinPoint.proceed();
        }
    }
}

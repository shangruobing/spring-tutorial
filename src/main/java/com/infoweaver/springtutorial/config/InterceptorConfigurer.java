package com.infoweaver.springtutorial.config;

import com.infoweaver.springtutorial.common.JwtInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ruobing Shang 2022-09-04 20:57
 */

// Disable JWT Auth

//@Configuration
//public class InterceptorConfigurer implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/user")
//                .excludePathPatterns("/user/login");
//    }
//}

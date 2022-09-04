package com.infoweaver.springtutorial.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author Ruobing Shang 2022-09-02 0:05
 */

@Configuration
public class AdapterConfigurer implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(@NotNull CorsRegistry registry) {
        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowCredentials(false)
                .allowedMethods("POST", "GET", "DELETE", "PUT", "OPTIONS")
                .allowedOrigins("*");
    }
}

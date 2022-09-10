package com.infoweaver.springtutorial.config;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

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

    /**
     * Delete StringHttpMessageConverter, to avoid Jackson parsing String errors.
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(x -> x instanceof StringHttpMessageConverter);
    }

}

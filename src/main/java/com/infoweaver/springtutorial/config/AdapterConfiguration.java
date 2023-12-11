package com.infoweaver.springtutorial.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author Ruobing Shang 2023-09-25 17:56
 */
@Configuration
public class AdapterConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOriginPatterns("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .exposedHeaders("Access-Control-Allow-Origin", "Access-Control-Allow-Credentials")
                /**
                 * 这里应该设置为true，代表允许发送cookie
                 */
                .allowCredentials(true)
                /**
                 * 设置1小时不进行跨域检查
                 */
                .maxAge(3600);
        WebMvcConfigurer.super.addCorsMappings(registry);
    }

    /**
     * Delete StringHttpMessageConverter, to avoid Jackson parsing String errors.
     *
     * @param converters initially an empty list of converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.removeIf(StringHttpMessageConverter.class::isInstance);
    }
}

//package com.infoweaver.springtutorial.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
//import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
///**
// * @author Ruobing Shang 2022-09-04 20:57
// */
//
//@Configuration
//public class ResourceConfigurer implements WebMvcConfigurer {
//    /**
//     * The access interface needs to add the api prefix,
//     * access to static resources is not required.
//     *
//     * @param configurer PathMatchConfigurer
//     */
//    @Override
//    public void configurePathMatch(PathMatchConfigurer configurer) {
//        configurer.addPathPrefix("/api",
//                c -> c.isAnnotationPresent(RestController.class) || c.isAnnotationPresent(Controller.class));
//    }
//
//    /**
//     * ResourceHandlerRegistry
//     *
//     * @param registry ResourceHandlerRegistry
//     */
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**")
//                .addResourceLocations("classpath:/META-INF/resources/")
//                .addResourceLocations("classpath:/resources/")
//                .addResourceLocations("classpath:/static/");
//    }
//
//}

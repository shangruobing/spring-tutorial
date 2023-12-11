package com.infoweaver.springtutorial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Ruobing Shang 2023-09-24 21:31
 */
@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI createOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Tutorial")
                        .description("Spring Tutorial API文档")
                        .version("v1")
                );
    }
}
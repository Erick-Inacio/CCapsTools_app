package com.ccapstools_app.config;

import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

public class OpenApiConfig {
    @Bean
    OpenAPI customApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("SETEC API Documentation")
                        .version("1.0.0")
                        .description("Documentação da API da SETEC")
                        .termsOfService("")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("")));
    }
}

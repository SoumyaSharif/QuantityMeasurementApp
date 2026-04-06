package com.app.quantitymeasurement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI quantityMeasurementOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Quantity Measurement API")
                        .description("Spring Boot APIs for quantity comparison, conversion, and arithmetic operations")
                        .version("1.0")
                        .contact(new Contact().name("Quantity Measurement Service")));
    }
}

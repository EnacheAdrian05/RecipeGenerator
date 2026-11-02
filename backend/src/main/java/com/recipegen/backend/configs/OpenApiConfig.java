package com.recipegen.backend.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@SuppressWarnings("unused")
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Recipe Generator API")
                        .version("1.0.0")
                        .description("Describes the functionalities of a recipe generator app, " +
                                "including authentification, allergy selection and the recipe " +
                                "generation itself.")
                        .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }
}
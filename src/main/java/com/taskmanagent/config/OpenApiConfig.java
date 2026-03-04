package com.taskmanagent.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Task Management API")
                        .description("API REST para gerenciamento de tarefas com operações de CRUD completas. " +
                                "Desenvolvida com Spring Boot e DynamoDB.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Desafio Técnico")
                                .email("cintiacristinaangelo@gmail.com")
                        )
                );
    }
}

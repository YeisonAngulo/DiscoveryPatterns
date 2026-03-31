package co.com.dp.yeisonangulo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI itemServiceOpenApi() {
        return new OpenAPI().info(new Info()
                .title("Item Service API")
                .version("v1")
                .description("Small Spring Boot service that creates items in memory and publishes Kafka messages.")
                .contact(new Contact().name("Yeison Angulo")));
    }
}

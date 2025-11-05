package com.gmnds.academy.infra.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info().title("Academy API")
                .version("0.1.0")
                .description("Sistema acadêmico para alunos")
                .license(new License().name("MIT").url("https://opensource.org/licenses/MIT")
                )
        );
    }
}

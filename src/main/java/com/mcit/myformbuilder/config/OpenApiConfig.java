package com.mcit.myformbuilder.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Fazulhaq Amin",
                        email = "example@gmail.com",
                        url = "https://github.com/Fazulhaq"
                ),
                description = "These APIs documentations are used for better understanding of project endpoints",
                title = "FormBuilder APIs' Documentations",
                version = "v1.0.1",
                license = @License(
                        name = "License Name",
                        url = "example.com"
                ),
                termsOfService = "Terms of services..."
        ),
        servers = {
                @Server(
                        description = "LOCAL ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://examplelearning.com"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "BearerToken"
                )
        }
)
@SecurityScheme(
        name = "BearerToken",
        description = "JWT Authentication description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)

@Configuration
public class OpenApiConfig {
//    @Bean
//    public OpenAPI openAPI(){
//        return new OpenAPI()
//                .info(new Info()
//                        .title("FormBuilder APIs' Documentations")
//                        .description("These APIs documentations are used for better understanding of project endpoints")
//                        .version("v1.0.1"));
//    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

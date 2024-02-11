package org.studyeasy.SpringRest.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;

@Configuration
@OpenAPIDefinition(
    info=@Info(
        title = "User API",
        version = "Version 1.0",
        contact = @Contact(
            name = "Siddharth Swagger", email = "sidver1201@gmail.com", url = "https://studyeasy.org"

        ),
        license = @License(
            name = "Apache 2.0" , url = "https://www.apache.org/licenses/License-2.0"
        ),
        termsOfService = "https://studyeasy.org/tos",
        description = "Springboot Restful API"
    )
)


public class SwaggerConfig {
    
}

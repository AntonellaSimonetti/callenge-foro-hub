package ar.antonella.forohub.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "ForoHub API",
        version = "1.0",
        description = "Documentación de endpoints de la API ForoHub"))
public class SwaggerConfig {
}

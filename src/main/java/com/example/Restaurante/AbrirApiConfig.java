package com.example.Restaurante;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "API Restaurante",
                version = "1.0",
                description = "Sistema de gestión de pedidos e inventario"
        )
)
@Configuration
public class AbrirApiConfig {

}

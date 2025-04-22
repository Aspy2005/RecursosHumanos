package com.example.demo;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Permitir CORS en todas las rutas
                .allowedOrigins("http://localhost:4200")  // Permitir solo las solicitudes desde Angular (localhost:4200)
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // Permitir estos métodos HTTP
                .allowedHeaders("*");  // Permitir todos los encabezados
    }
}

package com.movie.restApi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // Dopuszcza wszystkie endpointy
                        .allowedOrigins("http://localhost:5173") // Zezwala na dostęp z tej domeny
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // Dopuszcza określone metody HTTP
                        .allowedHeaders("*") // Dopuszcza wszystkie nagłówki
                        .allowCredentials(true); // Zezwala na uwierzytelnienie (np. ciasteczka)
            }
        };
    }
}
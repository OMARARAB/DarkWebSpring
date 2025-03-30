package com.example.DarkWebM.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // ✅ Allow all endpoints
                        .allowedOrigins("*")  // ✅ Allow all domains (Change for production)
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // ✅ Allow these methods
                        .allowedHeaders("*");  // ✅ Allow all headers
            }
        };
    }
}
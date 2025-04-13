package com.example.DarkWebM.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // ✅ Allow all endpoints
                .allowedOrigins("*")  // ✅ Allow all origins (adjust for prod)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // ✅ Allowed methods
                .allowedHeaders("*");  // ✅ Allow all headers
    }
}

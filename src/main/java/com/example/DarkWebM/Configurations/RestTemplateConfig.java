package com.example.DarkWebM.Configurations;

// Import necessary classes from Spring framework
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate; // Importing the RestTemplate class

/*
Configuration class for setting up a RestTemplate bean.
The RestTemplate is a synchronous client used to make HTTP requests.
*/
@Configuration // Indicates that this class contains Spring configuration
public class RestTemplateConfig {

    // Method that creates and configures a RestTemplate bean
    @Bean // Marks this method as a bean definition, so the RestTemplate will be managed by Spring's application context
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Creates and returns a new instance of RestTemplate for making REST calls
    }
}

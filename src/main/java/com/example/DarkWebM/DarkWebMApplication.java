package com.example.DarkWebM;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.DarkWebM.Repository")  // Specify your repository package here
@EntityScan(basePackages = "com.example.DarkWebM.Model")  // Specify your entity package here
public class DarkWebMApplication {

	public static void main(String[] args) {
		SpringApplication.run(DarkWebMApplication.class, args);
	}
}

package com.example.DarkWebM.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data // Lombok annotation to generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor

// DTO class to handle userId and single keyword
public class ScrapeRequest {
    private Long id;  // User ID
    private String keyword; // Single keyword for scraping

}

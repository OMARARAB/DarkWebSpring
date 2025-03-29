package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.LeakResult;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Repository.UserRepository;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class LeakCheckService {

    private final RestTemplate restTemplate;
    private final UserRepository userRepository;
    private static final String FLASK_API_URL = "https://scan-yours-iguard-vercel.vercel.app/api/checkleak";

    public LeakCheckService(RestTemplate restTemplate, UserRepository userRepository) {
        this.restTemplate = restTemplate;
        this.userRepository = userRepository;
    }

    /**
     * Retrieves a user's data from the database and checks for leaked data.
     *
     * @param userId The ID of the user.
     * @return LeakResult object containing leak details.
     */
    public LeakResult checkUserLeaks(Long userId) {
        // Fetch user from database
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + userId));

        // Prepare request payload
        String jsonBody = String.format(
                "{\"username\": \"%s\", \"email\": \"%s\", \"phone\": \"%s\", \"password\": \"%s\"}",
                user.getUsername(), user.getEmail(), user.getPhone(), user.getPassword()
        );

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the request entity
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        try {
            // Send POST request to Flask API
            ResponseEntity<Map> response = restTemplate.exchange(FLASK_API_URL, HttpMethod.POST, entity, Map.class);

            // Process response
            if (response.getBody() != null) {
                return new LeakResult(response.getBody());
            } else {
                return new LeakResult(Map.of("error", "No data found"));
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to Flask API: " + e.getMessage(), e);
        }
    }
}

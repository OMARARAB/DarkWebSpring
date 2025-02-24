package com.example.DarkWebM.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;

@Service
public class AhmiaApiService {

    private final RestTemplate restTemplate;

    public AhmiaApiService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sends a POST request to the Flask API and retrieves search results.
     *
     * @param query The search keyword to send.
     * @return The JSON response as a List of Maps.
     */
    public List<Map<String, Object>> searchAhmia(String query) {
        // Flask API URL
        String flaskApiUrl = "http://127.0.0.1:5001/api/search";

        // Create the JSON request body
        String jsonBody = "{\"query\": \"" + query + "\"}";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the body and headers
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // Send POST request and retrieve the response
        try {
            // Change the response type to Map
            ResponseEntity<Map> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, entity, Map.class);

            // Check if the response contains "results"
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("results")) {
                // Return the results if present
                return (List<Map<String, Object>>) responseBody.get("results");
            } else {
                // Return an empty list if no results found
                return List.of();
            }
        } catch (Exception e) {
            throw new RuntimeException("Error connecting to Flask API: " + e.getMessage(), e);
        }
    }
}

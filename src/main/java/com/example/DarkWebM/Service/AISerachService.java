package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.SearchResult;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AISerachService {

    private final RestTemplate restTemplate;

    public AISerachService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Sends a POST request to the Flask API and retrieves search results.
     *
     * @param query The search keyword to send.
     * @return The JSON response as a List of SearchResult objects.
     */
    public List<SearchResult> performDeepSearch(String query) {
        // Flask API URL
        String flaskApiUrl = "https://-ai.app/api/";

        // Create the JSON request body with the 'query'
        String jsonBody = "{\"query\": \"" + query + "\"}";

        // Set headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        // Create the HttpEntity with the body and headers
        HttpEntity<String> entity = new HttpEntity<>(jsonBody, headers);

        // Send POST request and retrieve the response
        try {
            // Log the outgoing request
            System.out.println("Sending request to Flask API: " + jsonBody);

            // Change the response type to Map
            ResponseEntity<Map> response = restTemplate.exchange(flaskApiUrl, HttpMethod.POST, entity, Map.class);

            // Log the raw response
            System.out.println("Response from Flask API: " + response);

            // Check if the response body is not null
            Map<String, Object> responseBody = response.getBody();
            if (responseBody != null && responseBody.containsKey("results")) {
                // Convert the results into a list of SearchResult objects
                List<Map<String, Object>> results = (List<Map<String, Object>>) responseBody.get("results");
                return results.stream().map(resultMap -> {
                    SearchResult result = new SearchResult(); // Use the no-argument constructor from Lombok
                    result.setCategory((String) resultMap.get("category"));
                    result.setTitle((String) resultMap.get("title"));
                    result.setLink((String) resultMap.get("link"));
                    result.setDescription((String) resultMap.get("description"));
                    return result;
                }).collect(Collectors.toList());
            } else {
                // Return an empty list if no results found
                System.out.println("No results found in the response");
                return List.of();
            }
        } catch (Exception e) {
            // Log the error
            System.err.println("Error connecting to Flask API: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error connecting to Flask API: " + e.getMessage(), e);
        }
    }
}

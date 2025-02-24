package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Service.AISerachService;
import com.example.DarkWebM.Model.SearchResult; // Import your custom SearchResult class
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/ai")
public class AICoreController {

    @Autowired
    private AISerachService aiSerachService;  // Assuming you have an AI service to perform deep search

    @PostMapping("/scrape")
    public ResponseEntity<?> scrape(@RequestBody Map<String, String> request) {
        String query = request.get("query");
        if (query == null || query.isEmpty()) {
            return ResponseEntity.badRequest().body("Query parameter is required.");
        }

        try {
            // Log the incoming request
            System.out.println("Received query: " + query);

            List<SearchResult> results = aiSerachService.performDeepSearch(query); // Using your custom SearchResult class

            // Log the results
            System.out.println("Results: " + results);

            if (results.isEmpty()) {
                return ResponseEntity.ok("No results found for query: " + query);
            }
            return ResponseEntity.ok(results);
        } catch (Exception e) {
            // Log the error
            System.err.println("Error processing request: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while processing the request: " + e.getMessage());
        }
    }
}

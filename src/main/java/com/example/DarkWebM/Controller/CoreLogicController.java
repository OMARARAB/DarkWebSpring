package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Model.ScrapeRequest;
import com.example.DarkWebM.Service.CoreLogicService;
import com.example.DarkWebM.Service.QueryService;
import com.example.DarkWebM.Model.Query;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/logic")
@RequiredArgsConstructor
public class CoreLogicController {

    private final CoreLogicService coreLogicService;
    private final QueryService queryService; // Inject QueryService to create queries
    private final UserService userService;

    // POST endpoint to scrape data based on a single keyword and userId
    @PostMapping("/scrape")
    public ResponseEntity<String> scrape(@RequestBody ScrapeRequest request) {
        // Validate that the id and keyword are not null or empty
        if (request.getId() == null || request.getKeyword() == null || request.getKeyword().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("User ID and Keyword must not be null or empty.");
        }

        // Retrieve user object using the id
        User user = getUserById(request.getId());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        // Create a Query object using QueryService and save it
        Query query = queryService.createQuery(user, request.getKeyword());

        // Call the service method to scrape using the provided single keyword and query object
        List<String> results = coreLogicService.scrapeForKeywords(List.of(request.getKeyword()), query);

        if (results.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No results found.");
        }

        // Return a single result for the scraped data (first match)
        return ResponseEntity.ok(results.get(0)); // Only return the result for the single keyword
    }

    // Helper method to get User by ID
    private User getUserById(Long userId) {
        return userService.getUserById(userId).orElse(null); // Fetch user by ID using UserService
    }
}

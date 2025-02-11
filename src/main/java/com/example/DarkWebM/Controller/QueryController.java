package com.example.DarkWebM.Controller;

// Import necessary classes
import com.example.DarkWebM.Model.Query;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Service.QueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a Spring REST controller
@RequestMapping("/api/query") // Maps HTTP requests to this controller at the specified base path
@RequiredArgsConstructor // Generates a constructor with required arguments for dependency injection
public class QueryController {

    private final QueryService queryService; // Injected service for handling query-related logic

    // POST endpoint to create a new query
    @PostMapping("/create") // Maps POST requests to this method
    public ResponseEntity<Query> createQuery(@RequestParam Long userId, @RequestParam String keywords) {
        // Create a User object with the given userId
        User user = new User();
        user.setId(userId); // Set the user ID to associate the query with the user
        Query query = queryService.createQuery(user, keywords); // Create the query using the service
        return new ResponseEntity<>(query, HttpStatus.CREATED); // Return 201 Created status with the new query
    }

    // GET endpoint to retrieve query history for a user, including only "found" results
    @GetMapping("/history/{userId}") // Maps GET requests to this method with a userId path variable
    public ResponseEntity<List<Query>> getQueryHistory(@PathVariable Long userId) {
        User user = new User(); // Create a new User object
        user.setId(userId); // Set the user ID
        List<Query> queries = queryService.getQueryHistory(user); // Fetch the query history for the user
        return ResponseEntity.ok(queries); // Return 200 OK with the query history
    }


    // GET endpoint to retrieve a specific query by query ID
    @GetMapping("/findquerybyid/{queryId}") // Maps GET requests to this method with a queryId path variable
    public ResponseEntity<Query> getQueryById(@PathVariable Long queryId) {
        return queryService.getQueryById(queryId) // Attempt to fetch the query
                .map(ResponseEntity::ok) // If present, return 200 OK with the query
                .orElse(ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    // GET endpoint to retrieve all queries
    @GetMapping("/findall") // Maps GET requests to this method to retrieve all queries
    public ResponseEntity<List<Query>> findAllQueries() {
        List<Query> queries = queryService.findAllQueries(); // Fetch all queries from the service
        return ResponseEntity.ok(queries); // Return 200 OK status with the list of all queries
    }


    // PUT endpoint to update an existing query by query ID
    @PutMapping("/update/{queryId}") // Maps PUT requests to this method with a queryId path variable
    public ResponseEntity<Query> updateQuery(@PathVariable Long queryId, @RequestBody String newKeywords) {
        Query updatedQuery = queryService.updateQuery(queryId, newKeywords); // Update the query with new keywords
        return ResponseEntity.ok(updatedQuery); // Return 200 OK with the updated query
    }

    // DELETE endpoint to delete a query by query ID
    @DeleteMapping("/delete/{queryId}") // Maps DELETE requests to this method with a queryId path variable
    public ResponseEntity<Void> deleteQuery(@PathVariable Long queryId) {
        queryService.deleteQuery(queryId); // Call the service to delete the query
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }
}

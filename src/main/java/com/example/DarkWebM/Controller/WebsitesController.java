package com.example.DarkWebM.Controller;

// Import necessary classes
import com.example.DarkWebM.Model.Website;
import com.example.DarkWebM.Service.WebsitesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a Spring REST controller
@RequestMapping("/api/websites") // Maps HTTP requests to this controller at the specified base path
@RequiredArgsConstructor // Generates a constructor with required arguments for dependency injection
public class WebsitesController {

    private final WebsitesService websitesService; // Injected service for handling website-related logic

    // Endpoint to add a new website
    @PostMapping("/save") // Maps POST requests to this method
    public ResponseEntity<Website> addWebsite(@RequestBody Website website) {
        // Call the service to add the new website
        Website savedWebsite = websitesService.addWebsite(website);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedWebsite); // Return 201 Created status with the saved website
    }

    // Endpoint to retrieve all websites
    @GetMapping("/all") // Maps GET requests to this method
    public ResponseEntity<List<Website>> getAllWebsites() {
        // Fetch all websites from the service
        List<Website> websites = websitesService.getAllWebsites();
        return ResponseEntity.ok(websites); // Return 200 OK status with the list of websites
    }

    // Endpoint to update an existing website
    @PutMapping("/update/{id}") // Maps PUT requests to this method with an ID path variable
    public ResponseEntity<Website> updateWebsite(@PathVariable Long id, @RequestBody Website updatedWebsite) {
        // Call the service to update the website with the specified ID
        Website website = websitesService.updateWebsite(id, updatedWebsite);
        if (website != null) {
            return ResponseEntity.ok(website); // If updated, return 200 OK with the updated website
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build(); // If not found, return 404 Not Found
    }

    // Endpoint to delete a website
    @DeleteMapping("/delete/{id}") // Maps DELETE requests to this method with an ID path variable
    public ResponseEntity<Void> deleteWebsite(@PathVariable Long id) {
        // Call the service to delete the website with the specified ID
        websitesService.deleteWebsite(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content status indicating successful deletion
    }
}

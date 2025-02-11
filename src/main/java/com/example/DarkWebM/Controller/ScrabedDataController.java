package com.example.DarkWebM.Controller;

// Import necessary classes
import com.example.DarkWebM.Model.ScrabedData;
import com.example.DarkWebM.Service.ScrabedDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Indicates that this class is a Spring REST controller
@RequestMapping("/api/scraped-data") // Maps HTTP requests to this controller at the specified base path
@RequiredArgsConstructor // Generates a constructor with required arguments for dependency injection
public class ScrabedDataController {

    private final ScrabedDataService scrabedDataService; // Injected service for handling scraped data logic

    // POST endpoint to create new scraped data
    @PostMapping("/save") // Maps POST requests to this method
    public ResponseEntity<ScrabedData> createScrapedData(@RequestBody ScrabedData scrapedData) {
        // Call the service to save the scraped data
        ScrabedData savedData = scrabedDataService.saveScrapedData(scrapedData);
        return new ResponseEntity<>(savedData, HttpStatus.CREATED); // Return 201 Created status with the saved data
    }

    // GET endpoint to retrieve all scraped data
    @GetMapping ("/all")// Maps GET requests to this method
    public ResponseEntity<List<ScrabedData>> getAllScrapedData() {
        // Fetch all scraped data from the service
        List<ScrabedData> scrapedDataList = scrabedDataService.getAllScrapedData();
        return ResponseEntity.ok(scrapedDataList); // Return 200 OK status with the list of scraped data
    }


    // DELETE endpoint to delete scraped data by ID
    @DeleteMapping("/{id}") // Maps DELETE requests to this method with an ID path variable
    public ResponseEntity<Void> deleteScrapedData(@PathVariable Long id) {
        // Call the service to delete the scraped data with the specified ID
        scrabedDataService.deleteScrapedData(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content status
    }
}

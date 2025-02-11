package com.example.DarkWebM.Service; // Defines the package for the service layer

import com.example.DarkWebM.Model.Website; // Importing the Website model
import com.example.DarkWebM.Repository.WebsitesRepository; // Importing the Websites repository
import lombok.RequiredArgsConstructor; // Importing Lombok's annotation for constructor injection
import org.springframework.stereotype.Service; // Importing Service annotation

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List; // Importing List interface
import java.util.Optional; // Importing Optional class for handling optional values

// Service class for managing website entities
@Service // Indicates that this class is a Spring service component
@RequiredArgsConstructor // Automatically generates a constructor with required arguments (final fields)
public class WebsitesService {

    private final WebsitesRepository websiteRepository; // Repository for website operations

    // Method to add a new website
    public Website addWebsite(Website website) {
        return websiteRepository.save(website); // Save and return the newly added website
    }

    // Method to retrieve all websites
    public List<Website> getAllWebsites() {
        return websiteRepository.findAll(); // Fetch and return the list of all websites
    }

    // Method to update an existing website
    public Website updateWebsite(Long id, Website updatedWebsite) { // Change Long to UUID for unique identification
        Optional<Website> website = websiteRepository.findById(id); // Find the website by ID
        if (website.isPresent()) { // If the website exists
            Website existingWebsite = website.get(); // Get the existing website
            // Update website fields with new values
            existingWebsite.setUrl(updatedWebsite.getUrl()); // Update the URL of the website
            return websiteRepository.save(existingWebsite); // Save and return the updated website
        }
        return null; // Or throw an exception to indicate the website was not found
    }

    // Method to delete a website by its ID
    public void deleteWebsite(Long id) { // Change Long to UUID for unique identification
        websiteRepository.deleteById(id); // Delete the website by ID
    }

    // Import URLs from a file and add them to the database
    public void importUrlsFromFile(String filePath) throws Exception {
        List<String> urls = Files.readAllLines(Paths.get(filePath));
        for (String url : urls) {
            if (!url.isBlank() && !websiteRepository.existsByUrl(url)) {
                Website website = new Website();
                website.setUrl(url);
                addWebsite(website);
            }
        }
    }
}

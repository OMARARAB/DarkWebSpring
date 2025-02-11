/*package com.example.DarkWebM.Configurations;
// Defines the package for this class, which is part of the "Configurations" module of your application.

import com.example.DarkWebM.Service.WebsitesService;
// Imports the WebsitesService, which contains the logic for handling the database operations related to websites.

import org.springframework.boot.CommandLineRunner;
// Imports the CommandLineRunner interface, which allows code to run at application startup.

import org.springframework.stereotype.Component;
// Imports the @Component annotation to register this class as a Spring-managed bean.

@Component
// Marks this class as a Spring-managed component so it can be detected and used during component scanning.
public class DataImporter implements CommandLineRunner {
// Implements the CommandLineRunner interface to execute code at the application startup.

    private final WebsitesService service;
    // Declares a dependency on the WebsitesService, which will be used to handle the logic of importing URLs.

    // Constructor-based dependency injection.
    // Spring automatically injects an instance of WebsitesService when this class is created.
    public DataImporter(WebsitesService service) {
        this.service = service; // Assigns the injected WebsitesService to the local variable.
    }

    @Override
    public void run(String... args) throws Exception {
        // The run method is executed automatically after the application context is initialized.
        // It allows you to run code on application startup.

        String filePath = "C:\\Storage\\Career\\Backend\\Java Projects\\DarkWebM\\Sites.txt";
        // Specifies the path to the file containing the list of onion URLs.
        // Update this path to point to the actual location of your file.

        service.importUrlsFromFile(filePath);
        // Calls the importUrlsFromFile method from the WebsitesService to read URLs from the file
        // and save them to the database.
    }
}
*/
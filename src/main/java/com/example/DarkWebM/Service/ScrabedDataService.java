package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.ScrabedData;
import com.example.DarkWebM.Repository.ScrabedDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScrabedDataService {

    private final ScrabedDataRepository scrapedDataRepository; // Injecting the ScrabedDataRepository

    // Method to save a new scraped data entry
    public ScrabedData saveScrapedData(ScrabedData scrapedData) {
        return scrapedDataRepository.save(scrapedData); // Save and return the scraped data
    }

    // Method to retrieve all scraped data entries
    public List<ScrabedData> getAllScrapedData() {
        return scrapedDataRepository.findAll(); // Fetch and return the list of all scraped data
    }

    // Method to find ScrabedData by queryId
    public List<ScrabedData> findByQuery(Long queryId) {
        return scrapedDataRepository.findByQuery_QueryId(queryId); // Fetch scraped data by queryId
    }

    // Method to delete a scraped data entry by its ID
    public void deleteScrapedData(Long id) {
        scrapedDataRepository.deleteById(id); // Delete the scraped data by its ID
    }
}

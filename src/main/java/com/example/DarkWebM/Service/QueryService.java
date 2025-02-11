package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.Query;
import com.example.DarkWebM.Model.ScrabedData;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Repository.QueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class QueryService {

    private final QueryRepository queryRepository; // Repository for query operations
    private final ScrabedDataService scrabedDataService; // Service for handling ScrabedData

    // Existing method to create a new query
    public Query createQuery(User user, String keywords) {
        if (user == null) {
            throw new IllegalArgumentException("User cannot be null.");
        }
        if (keywords == null || keywords.trim().isEmpty()) {
            throw new IllegalArgumentException("Keywords cannot be null or empty.");
        }

        Query query = new Query();
        query.setUser(user); // Set the user
        query.setKeywords(keywords); // Set the keywords
        return queryRepository.save(query); // Save and return the new query
    }

    // New method to get query history, filtered for "found" results only
    public List<Query> getQueryHistory(User user) {
        // Fetch all queries by user
        List<Query> queries = queryRepository.findByUserId(user.getId());

        // Loop through each query and filter out "Not found" results
        for (Query query : queries) {
            // Get the scraped data associated with the query
            List<ScrabedData> scrapedDataList = scrabedDataService.findByQuery(query.getQueryId());

            // Filter out "Not found" results from scraped data
            List<ScrabedData> foundData = scrapedDataList.stream()
                    .filter(scrabedData -> scrabedData.getContent().contains("Found"))
                    .collect(Collectors.toList());

            // Update the query's scraped data to only include "found" content
            query.setScrapedData(foundData);
        }

        return queries; // Return the list of queries with found scraped data
    }

    // Existing method to retrieve a query by its ID
    public Optional<Query> getQueryById(Long queryId) {
        return queryRepository.findById(queryId); // Fetch and return the query wrapped in Optional
    }

    // Existing method to find all queries
    public List<Query> findAllQueries() {
        return queryRepository.findAll(); // Retrieve all queries from the repository
    }

    // Existing method to update an existing query's keywords
    @Transactional
    public Query updateQuery(Long queryId, String newKeywords) {
        if (newKeywords == null || newKeywords.trim().isEmpty()) {
            throw new IllegalArgumentException("New keywords cannot be null or empty.");
        }

        Query query = queryRepository.findById(queryId)
                .orElseThrow(() -> new IllegalArgumentException("Query with ID " + queryId + " not found."));
        query.setKeywords(newKeywords); // Update the keywords
        return queryRepository.save(query); // Save and return the updated query
    }

    // Existing method to delete a query by its ID
    public void deleteQuery(Long queryId) {
        if (!queryRepository.existsById(queryId)) {
            throw new IllegalArgumentException("Query with ID " + queryId + " does not exist.");
        }
        queryRepository.deleteById(queryId); // Delete the query by its ID
    }
}

package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.ScrabedData;
import com.example.DarkWebM.Model.Website;
import com.example.DarkWebM.Model.Query;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CoreLogicService {

    private final WebsitesService websiteService;
    private final ScrabedDataService scrabedDataService;
    private final RestTemplate restTemplate;
    private static final String FLASK_API_URL = "https://till-bright-upset-england.trycloudflare.com/corelogic/scrape";
    private final Logger logger = LoggerFactory.getLogger(CoreLogicService.class);

    public List<String> scrapeForKeywords(List<String> keywords, Query query) {
        List<String> results = new ArrayList<>();

        if (keywords == null || keywords.isEmpty()) {
            logger.warn("No keywords provided for scraping.");
            results.add("No keywords provided for scraping.");
            return results;
        }

        List<Website> websites = websiteService.getAllWebsites();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Content-Type", "application/json");

        for (Website website : websites) {
            try {
                Map<String, Object> requestBody = Map.of(
                        "url", website.getUrl(),
                        "keywords", keywords
                );

                HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

                ResponseEntity<Map> response = restTemplate.exchange(
                        FLASK_API_URL,
                        HttpMethod.POST,
                        entity,
                        Map.class
                );

                String result = processApiResponse(response, website);
                logger.info("Processing result for {}: {}", website.getUrl(), result);
                results.add(result);

                // Only save "Found" results to ScrabedData
                if (result.contains("Found on")) {
                    ScrabedData scrabedData = new ScrabedData();
                    scrabedData.setWebsite(website);
                    scrabedData.setQuery(query);
                    scrabedData.setContent("Found on " + website.getUrl());  // Set the content to "Found"
                    scrabedDataService.saveScrapedData(scrabedData);  // Save the result
                }

            } catch (Exception e) {
                String errorMessage = String.format("Error scraping website %s: %s", website.getUrl(), e.getMessage());
                logger.error(errorMessage);
                results.add(errorMessage);
            }
        }

        return results;
    }

    private String processApiResponse(ResponseEntity<Map> response, Website website) {
        Map<String, Object> responseBody = response.getBody();

        if (responseBody != null) {
            if (response.getStatusCode().is2xxSuccessful() && responseBody.containsKey("found_keywords")) {
                List<String> foundKeywords = (List<String>) responseBody.get("found_keywords");

                if (foundKeywords != null && !foundKeywords.isEmpty()) {
                    return String.format("Found on %s", website.getUrl());
                } else {
                    return String.format("Not found on %s", website.getUrl());
                }
            } else {
                String message = (String) responseBody.getOrDefault("message", "Unknown error occurred.");
                return String.format("Not found on %s. Error: %s", website.getUrl(), message);
            }
        } else {
            return String.format("No response from API for %s.", website.getUrl());
        }
    }
}

package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Service.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ScanController {

    private final ApiService apiService;

    @PostMapping("/scrape")
    public List<Map<String, Object>> search(@RequestBody Map<String, String> requestBody,
                                            @RequestHeader Map<String, String> headers) {
        System.out.println("🔹 Incoming request headers: " + headers);
        System.out.println("🔹 Request body: " + requestBody);

        String query = requestBody.get("query");
        return apiService.search(query);
    }

}

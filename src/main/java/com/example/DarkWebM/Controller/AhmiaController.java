package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Service.AhmiaApiService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@AllArgsConstructor
@RestController
@RequestMapping("/api/ahmia")
public class AhmiaController {

    private final AhmiaApiService ahmiaApiService;

    @PostMapping("/scrape")
    public List<Map<String, Object>> search(@RequestBody Map<String, String> requestBody) {
        String query = requestBody.get("query");
        return ahmiaApiService.searchAhmia(query);
    }

}

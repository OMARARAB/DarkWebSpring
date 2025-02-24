package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Model.LeakResult;
import com.example.DarkWebM.Service.LeakCheckService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/leaks")
public class LeakCheckController {

    private final LeakCheckService leakCheckService;

    public LeakCheckController(LeakCheckService leakCheckService) {
        this.leakCheckService = leakCheckService;
    }

    // 🔹 Check leaks for the currently authenticated user
    @GetMapping("/check")
    public ResponseEntity<LeakResult> checkLeaks(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) {
            return ResponseEntity.status(403).build(); // Forbidden if not authenticated
        }

        Long userId = ((com.example.DarkWebM.Model.User) userDetails).getId();
        LeakResult result = leakCheckService.checkUserLeaks(userId);
        return ResponseEntity.ok(result);
    }
}

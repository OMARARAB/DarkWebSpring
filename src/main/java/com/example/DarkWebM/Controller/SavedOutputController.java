package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Model.SavedOutput;
import com.example.DarkWebM.Service.SavedOutputService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/saved-outputs")
public class SavedOutputController {

    private final SavedOutputService savedOutputService;

    public SavedOutputController(SavedOutputService savedOutputService) {
        this.savedOutputService = savedOutputService;
    }

    // Save a new output
    @PostMapping("/save/{userId}")
    public ResponseEntity<?> saveOutput(@PathVariable Long userId, @RequestBody SavedOutput savedOutput) {
        try {
            SavedOutput saved = savedOutputService.saveOutput(userId, savedOutput);
            return ResponseEntity.ok(saved);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get saved outputs by user ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SavedOutput>> getUserSavedOutputs(@PathVariable Long userId) {
        List<SavedOutput> outputs = savedOutputService.getUserSavedOutputs(userId);
        return ResponseEntity.ok(outputs);
    }

    // Delete a saved output
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSavedOutput(@PathVariable Long id) {
        savedOutputService.deleteSavedOutput(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}

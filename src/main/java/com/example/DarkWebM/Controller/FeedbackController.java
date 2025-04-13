package com.example.DarkWebM.Controller;

import com.example.DarkWebM.Model.FeedBack;
import com.example.DarkWebM.Service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    // Endpoint to submit feedback (POST)
    @PostMapping("/submit")
    public FeedBack submitFeedback(@RequestBody FeedBack feedback) {
        return feedbackService.submitFeedback(feedback);
    }

    // Endpoint to get all feedback (GET)
    @GetMapping
    public List<FeedBack> getAllFeedback() {
        return feedbackService.getAllFeedback();
    }

    // Endpoint to get feedback by ID (GET)
    @GetMapping("/{id}")
    public FeedBack getFeedbackById(@PathVariable Long id) {
        return feedbackService.getFeedbackById(id);
    }
}

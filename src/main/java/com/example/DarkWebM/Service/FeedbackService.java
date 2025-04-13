package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.FeedBack;
import com.example.DarkWebM.Repository.FeedBackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    @Autowired
    private FeedBackRepository feedbackRepository;

    // Method to submit feedback
    public FeedBack submitFeedback(FeedBack feedback) {
        // Optionally, you can add additional logic here before saving
        return feedbackRepository.save(feedback);
    }

    // Method to retrieve all feedback
    public List<FeedBack> getAllFeedback() {
        return feedbackRepository.findAll();
    }

    // Method to retrieve feedback by ID
    public FeedBack getFeedbackById(Long id) {
        return feedbackRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Feedback not found"));
    }
}

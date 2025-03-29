package com.example.DarkWebM.Service;

import com.example.DarkWebM.Model.SavedOutput;
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Repository.SavedOutputRepository;
import com.example.DarkWebM.Repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SavedOutputService {

    private final SavedOutputRepository savedOutputRepository;
    private final UserRepository userRepository;

    public SavedOutputService(SavedOutputRepository savedOutputRepository, UserRepository userRepository) {
        this.savedOutputRepository = savedOutputRepository;
        this.userRepository = userRepository;
    }

    // Save a new output
    public SavedOutput saveOutput(Long userId, SavedOutput savedOutput) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isPresent()) {
            savedOutput.setUser(user.get());
            return savedOutputRepository.save(savedOutput);
        }
        throw new RuntimeException("User not found");
    }

    // Get saved outputs by user ID
    public List<SavedOutput> getUserSavedOutputs(Long userId) {
        return savedOutputRepository.findByUserId(userId);
    }

    // Delete a saved output
    public void deleteSavedOutput(Long id) {
        savedOutputRepository.deleteById(id);
    }
}

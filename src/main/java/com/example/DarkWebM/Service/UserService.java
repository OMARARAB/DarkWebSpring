package com.example.DarkWebM.Service; // Defines the package for the service layer

import com.example.DarkWebM.Model.User; // Importing the User model
import com.example.DarkWebM.Repository.UserRepository; // Importing the User repository
import lombok.RequiredArgsConstructor; // Importing Lombok's annotation for constructor injection
import org.springframework.stereotype.Service; // Importing Service annotation

import java.util.List; // Importing List interface
import java.util.Optional; // Importing Optional class for handling optional values

// Service class for managing user entities
@Service // Indicates that this class is a Spring service component
@RequiredArgsConstructor // Automatically generates a constructor with required arguments (final fields)
public class UserService {

    private final UserRepository userRepository; // Repository for user operations

    // Method to create a new user
    public User createUser(User user) {
        return userRepository.save(user); // Save and return the newly created user
    }

    // Method to retrieve a user by their ID
    public Optional<User> getUserById(Long id) { // Consider changing to UUID for unique identification
        return userRepository.findById(id); // Fetch and return the user by ID, wrapped in Optional
    }

    // Method to retrieve all users
    public List<User> getAllUsers() {
        return userRepository.findAll(); // Fetch and return the list of all users
    }

    // Method to update an existing user
    public User updateUser(Long id, User updatedUser) { // Consider changing to UUID for unique identification
        Optional<User> existingUser = userRepository.findById(id); // Find the user by ID
        if (existingUser.isPresent()) { // If the user exists
            User user = existingUser.get(); // Get the existing user
            // Update user fields with new values
            user.setUsername(updatedUser.getUsername());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            return userRepository.save(user); // Save and return the updated user
        }
        return null; // Or throw an exception to indicate the user was not found
    }

    // Method to delete a user by their ID
    public void deleteUser(Long id) { // Consider changing to UUID for unique identification
        userRepository.deleteById(id); // Delete the user by ID
    }
}

package com.example.DarkWebM.Controller;

// Import necessary classes
import com.example.DarkWebM.Model.User;
import com.example.DarkWebM.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController // Indicates that this class is a Spring REST controller
@RequestMapping("/api/users") // Maps HTTP requests to this controller at the specified base path
@RequiredArgsConstructor // Generates a constructor with required arguments for dependency injection
public class UserController {

    private final UserService userService; // Injected service for handling user-related logic

    // Endpoint to create a new user
    @PostMapping("/save") // Maps POST requests to this method
    public ResponseEntity<User> createUser(@RequestBody User user) {
        // Call the service to create a new user
        User createdUser = userService.createUser(user);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED); // Return 201 Created status with the created user
    }

    // Endpoint to get a user by ID
    @GetMapping("/get/{id}") // Maps GET requests to this method with an ID path variable
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        // Fetch the user from the service using the provided ID
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok) // If user is found, return 200 OK status with the user
                .orElseGet(() -> ResponseEntity.notFound().build()); // If not found, return 404 Not Found
    }

    // Endpoint to get all users
    @GetMapping("/all") // Maps GET requests to this method
    public ResponseEntity<List<User>> getAllUsers() {
        // Fetch all users from the service
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK); // Return 200 OK status with the list of users
    }

    // Endpoint to update a user
    @PutMapping("/update/{id}") // Maps PUT requests to this method with an ID path variable
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        // Call the service to update the user with the specified ID
        User user = userService.updateUser(id, updatedUser);
        return user != null ? ResponseEntity.ok(user) // If updated, return 200 OK with the updated user
                : ResponseEntity.notFound().build(); // If not found, return 404 Not Found
    }

    // Endpoint to delete a user
    @DeleteMapping("delete/{id}") // Maps DELETE requests to this method with an ID path variable
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        // Call the service to delete the user with the specified ID
        userService.deleteUser(id);
        return ResponseEntity.noContent().build(); // Return 204 No Content status indicating successful deletion
    }
}

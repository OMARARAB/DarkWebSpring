package com.example.DarkWebM.Repository; // Defines the package for the repository

import com.example.DarkWebM.Model.User; // Importing the User model
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository for standard CRUD operations
import org.springframework.stereotype.Repository; // Importing Repository annotation

// Repository interface for the User entity, extending JpaRepository
@Repository // Indicates that this interface is a Spring Data repository
public interface UserRepository extends JpaRepository<User, Long> {
    // No additional methods are defined here, but inherited methods from JpaRepository can be used for CRUD operations.
}

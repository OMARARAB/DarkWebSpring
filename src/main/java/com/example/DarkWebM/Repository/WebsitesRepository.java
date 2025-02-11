package com.example.DarkWebM.Repository; // Defines the package for the repository

import com.example.DarkWebM.Model.Website; // Importing the Website model
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository for standard CRUD operations
import org.springframework.stereotype.Repository; // Importing Repository annotation

// Repository interface for the Website entity, extending JpaRepository
@Repository // Indicates that this interface is a Spring Data repository
public interface WebsitesRepository extends JpaRepository<Website, Long> {
    // No additional methods are defined here, but inherited methods from JpaRepository can be used for CRUD operations.

    boolean existsByUrl(String url); // Method to check if a URL already exists

}

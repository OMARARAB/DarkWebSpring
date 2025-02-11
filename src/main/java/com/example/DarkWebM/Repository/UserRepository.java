package com.example.DarkWebM.Repository; // Defines the package for the repository

import com.example.DarkWebM.Model.User; // Importing the User model
import org.springframework.data.jpa.repository.JpaRepository; // Importing JpaRepository for standard CRUD operations
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository; // Importing Repository annotation

import java.util.Optional;

// Repository interface for the User entity, extending JpaRepository
@Repository // Indicates that this interface is a Spring Data repository
public interface UserRepository extends JpaRepository<User, Long> {


    @Query("SELECT u FROM User u WHERE u.email = :email ORDER BY u.id ASC LIMIT 1")
    Optional<User> findByEmail(@Param("email") String email);

}

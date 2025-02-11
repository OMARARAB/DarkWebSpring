package com.example.DarkWebM.Model;

// Import necessary classes from Jakarta Persistence and Lombok libraries
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Specifies the table name in the database
@Data // Lombok annotation to automatically generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class User {

    @Id // Specifies that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the value of this field
    private Long id; // Unique identifier for each user

    @Column(nullable = false, unique = true, name = "username") // Defines a column with unique and not-null constraints
    private String username; // Username for the user, must be unique

    @Column(nullable = false, unique = true, name = "email") // Defines a column with unique and not-null constraints
    private String email; // Email for the user, must be unique

    @Column(nullable = false, name = "password") // Defines a column with a not-null constraint
    private String password; // Password for the user, cannot be null

    @Column(nullable = false, name = "role") // Defines a column with a not-null constraint
    @Enumerated(EnumType.STRING) // Indicates that the enum will be stored as a string in the database
    private Role role; // Role of the user, represented as an enum

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true) // Establishes a One-to-Many relationship with Query
    @JsonBackReference
    @JsonIgnore // Prevent infinite recursion
    private List<Query> queries; // List to hold associated Query objects for the user
}

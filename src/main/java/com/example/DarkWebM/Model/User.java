package com.example.DarkWebM.Model;

// Import necessary classes from Jakarta Persistence and Lombok libraries
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Entity // Marks this class as a JPA entity
@Table(name = "users") // Specifies the table name in the database
@Data // Lombok annotation to automatically generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
@Builder
public class User implements UserDetails {

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}

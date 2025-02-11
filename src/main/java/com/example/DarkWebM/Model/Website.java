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
@Table(name = "websites") // Specifies the table name in the database
@Data // Lombok annotation to automatically generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Website {

    @Id // Specifies that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the value of this field
    private Long websiteId; // Unique identifier for each website

    @Column(nullable = false, name = "name") // Defines a column with a not-null constraint
    private String name = "website"; // Name of the website, defaulting to "website"

    @Column(nullable = false, name = "url", unique = true) // URL column with not-null and unique constraints
    private String url; // URL of the website, must be unique and not null

    @OneToMany(mappedBy = "website", cascade = CascadeType.ALL, orphanRemoval = true) // Establishes a One-to-Many relationship with ScrapedData
    @JsonBackReference
    @JsonIgnore // Prevent recursive serialization of scrapedData from Website
    private List<ScrabedData> scrapedData; // List to hold associated ScrapedData objects for the website
}

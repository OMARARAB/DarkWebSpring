package com.example.DarkWebM.Model;

// Import necessary classes from Jakarta Persistence and Lombok libraries
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Indicates that this class is a JPA entity
@Table(name = "scrabed_data") // Specifies the table name in the database (using snake_case for database convention)
@Data // Lombok annotation to generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class ScrabedData {

    @Id // Specifies that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the value of this field
    private Long dataId; // Unique identifier for each scraped data entry

    @ManyToOne // Establishes a Many-to-One relationship with the Website entity
    @JoinColumn(name = "website_id", nullable = false) // Specifies the foreign key column in the scraped_data table
    @JsonBackReference
    private Website website; // Reference to the Website entity from which this data was scraped

    @ManyToOne // Establishes a Many-to-One relationship with the Query entity
    @JoinColumn(name = "query_id", nullable = false) // Specifies the foreign key column in the scraped_data table
    @JsonBackReference
    private Query query; // Reference to the Query that this scraped data corresponds to

    @Column(nullable = false, name = "content") // Specifies that this field is a column in the table
    private String content; // The content that was scraped from the dark web
}

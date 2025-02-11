package com.example.DarkWebM.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Entity // Indicates that this class is a JPA entity
@Table(name = "queries") // Specifies the table name in the database
@Data // Lombok annotation to generate getters, setters, and other utility methods
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class Query {

    @Id // Specifies that this field is the primary key
    @GeneratedValue(strategy = GenerationType.AUTO) // Automatically generates the value of this field
    private Long queryId; // Unique identifier for each query

    @ManyToOne // Establishes a Many-to-One relationship with the User entity
    @JoinColumn(name = "id", nullable = false) // Specifies the foreign key column in the queries table
    @JsonManagedReference
    @JsonBackReference
    private User user; // Reference to the User who created the query

    @Column(nullable = false, name = "keywords") // Specifies that this field is a column in the table
    private String keywords; // The keywords associated with the query

    @OneToMany(mappedBy = "query", cascade = CascadeType.ALL, orphanRemoval = true) // Establishes a one-to-many relationship with ScrabedData
    @JsonManagedReference // Manage the relationship for ScrabedData
    @JsonBackReference
    private List<ScrabedData> scrapedData; // List to hold related ScrapedData items

}

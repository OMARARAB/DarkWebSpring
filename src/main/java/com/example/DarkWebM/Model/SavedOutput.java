package com.example.DarkWebM.Model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "saved_outputs")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SavedOutput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, name = "title")
    private String title;

    @Column(nullable = false, name = "category")
    private String category;

    @Column(nullable = false, name = "link")
    private String link;

    @Column(nullable = false, name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, name = "onion_address")
    private String onionAddress;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // Relationship to User
}

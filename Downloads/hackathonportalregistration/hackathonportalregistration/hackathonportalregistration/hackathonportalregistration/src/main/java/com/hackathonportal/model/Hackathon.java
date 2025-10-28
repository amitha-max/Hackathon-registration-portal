package com.hackathonportal.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "hackathons")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hackathon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hackathon_id")
    private Long hackathonId;

    @NotBlank(message = "Title is required")
    private String title;

    @NotBlank(message = "Theme is required")
    private String theme;

    private LocalDate date;

    @Positive(message = "Max participants must be positive")
    @Column(name = "max_participants")
    private Integer maxParticipants;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    // Removed registrations list to prevent lazy loading issues
}
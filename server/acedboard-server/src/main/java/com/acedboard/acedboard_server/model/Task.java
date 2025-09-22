package com.acedboard.acedboard_server.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity @Getter @Setter @NoArgsConstructor
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) private Long id;
    private Long projectId;
    private String title;
    private String description;
    private String status ="TO DO";
    private String priority ="MEDIUM";
    private Instant dueAt;
    private Integer estimatedMinutes;
    private Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    
}

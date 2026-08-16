package org.camp.camp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Entity
@Data
@Table(name = "camp_channels")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampChannel {
    @Id
    @GeneratedValue
    @Column(nullable = false, updatable = false)
    private UUID id;

    @Column(name = "camp_id", nullable = false)
    private UUID campId;

    @Column(nullable = false)
    private String name;

    private String description;

    private String type;

    @Column(name = "created_at",  nullable = false,  updatable = false)
    private Instant createdAt;
    @Column(name = "updated_at",  nullable = false)
    private Instant updatedAt;

    // later add the delete functionality to not delete the channel just mark it as deleted not actually delete it from the database

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        updatedAt = Instant.now();
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = Instant.now();
    }
}

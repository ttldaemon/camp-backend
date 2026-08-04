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
@Builder
@Table(name = "camp_invitations", uniqueConstraints = @UniqueConstraint(columnNames = {"camp_id", "invited_email"}))
@NoArgsConstructor
@AllArgsConstructor
public class CampInvitation {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "camp_id", nullable = false)
    private UUID campId;

    @Column(name = "invited_email",  nullable = false)
    private String invitedEmail;

    @Column(name = "invited_by", nullable = false)
    private UUID invitedBy;

    private Role role;
    private Status status;

    @Column(name = "created_at",  nullable = false,  updatable = false)
    private Instant createdAt;

    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

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

package org.camp.camp.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.util.UUID;

enum Status {
    PENDING,
    ACCEPTED,
    DECLINED,
    EXPIRED,
    REVOKED
}

@Data
@Builder
@Entity
@Table(name = "camp_invites")
@AllArgsConstructor
public class CampInviteEntity {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "camp_id", nullable = false)
    private UUID campId;

    @Column(name = "invited_by", nullable = false)
    private UUID invitedBy;

    @Column(name = "invitee_email", nullable = false)
    private String inviteeEmail;

    private Status status;

    @Column(name = "created_at",  nullable = false,  updatable = false)
    private Instant createdAt;
    @Column(name = "expires_at")
    private Instant expiresAt;

    @Column(name = "responded_at")
    private Instant respondedAt;

    private boolean accepted;

    @PrePersist
    void onCreate() {
        createdAt = Instant.now();
        accepted = false;
        status = Status.PENDING;
    }
}

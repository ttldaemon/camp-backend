package org.camp.camp.emails.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CampInviteRequest {
    private UUID campId;
    private UUID invitedBy;
    private String inviteeEmail;
    private Instant expiresAt = Instant.now().plusSeconds(86400);
}

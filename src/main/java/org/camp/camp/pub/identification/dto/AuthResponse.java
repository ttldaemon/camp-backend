package org.camp.camp.pub.identification.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@AllArgsConstructor
@Builder
public class AuthResponse {
    private String token;
    private long expiresIn;
    private UserSummary user;

    @Data
    @AllArgsConstructor
    @Builder
    public static class UserSummary {
        private UUID id;
        private String userName;
        private String displayName;
        private String email;
    }
}

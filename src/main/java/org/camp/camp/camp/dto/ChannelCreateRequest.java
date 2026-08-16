package org.camp.camp.camp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChannelCreateRequest {
    private UUID campId;
    private String name;
    private String description;
    private String type;
}

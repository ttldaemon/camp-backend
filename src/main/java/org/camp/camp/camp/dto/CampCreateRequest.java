package org.camp.camp.camp.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.camp.camp.models.Visibility;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Data
public class CampCreateRequest {

    @Size(min = 4, max = 20, message = "Name of camp should be between 4 and 20 letters")
    private String name;

    @NotBlank
    private String slug;
    @NotBlank
    private String description;
    @Column(name = "avatar_url")
    private String avatarUrl;
    @NotBlank
    private Visibility visibility;
    @NotBlank
    @Column(name = "owner_id")
    private UUID ownerId;

    private List<String> tags;
}

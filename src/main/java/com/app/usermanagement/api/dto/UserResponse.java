package com.app.usermanagement.api.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.app.usermanagement.api.model.Phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


//Json Response del servicio con la informacion que entrega el servidor.

@Data
@Schema(description = "User Response DTO for registering a new user")
public class UserResponse {

    @Schema(description = "Unique identifier of the user", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Creation date of the user", example = "2024-09-06T12:30:00")
    private LocalDateTime created;

    @Schema(description = "Last modified date of the user", example = "2024-09-06T12:30:00")
    private LocalDateTime modified;

    @Schema(description = "Last login date of the user", example = "2024-09-06T12:30:00")
    private LocalDateTime lastLogin;

    @Schema(description = "Authentication token of the user", example = "abc123token")
    private String token;

    @Schema(description = "Status indicating if the user is active", example = "true")
    private boolean active;

    @Schema(description = "List of the user's phone numbers")
    private List<Phone> phones;
}
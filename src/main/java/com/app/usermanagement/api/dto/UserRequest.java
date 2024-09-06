package com.app.usermanagement.api.dto;

import java.util.List;
import java.util.UUID;

import com.app.usermanagement.api.model.Phone;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


// Json Request del servicio con la informacion que entrega el cliente.

@Data
@Schema(description = "User Request DTO for registering a new user")
public class UserRequest {

    @Schema(description = "Unique identifier of the user", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID id;

    @Schema(description = "Name of the user", example = "John Doe")
    private String name;

    @Schema(description = "Email of the user", example = "john.doe@example.com")
    private String email;

    @Schema(description = "Password of the user", example = "SecurePassword123", required = true)
    private String password;

    @Schema(description = "List of user's phone numbers")
    private List<Phone> phones;
}

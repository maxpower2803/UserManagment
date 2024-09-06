package com.app.usermanagement.api.model;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;


// entidad que contiene los atributos del objeto telefono.

@Entity
@Data
@Schema(description = "Phone entity representing a user's phone details")
public class Phone {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(description = "Unique identifier of the phone", example = "1")
    private Long id;
    
    @Schema(description = "Phone number", example = "123-456-7890", required = true)
    private String number;
    
    @Schema(description = "City code", example = "01")
    private String cityCode;
    
    @Schema(description = "Country code", example = "US")
    private String countryCode;
}

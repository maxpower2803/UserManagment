package com.app.usermanagement.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.usermanagement.api.dto.UserRequest;
import com.app.usermanagement.api.dto.UserResponse;
import com.app.usermanagement.api.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Controlador Usuarios", description = "Permite la creacion de usuarios")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService; //inyectamos el servicio para crear usuarios
    
    @PostMapping
    @Operation(
            summary = "Register a new user",
            description = "Creates a new user and returns the user details.",
            responses = {
                @ApiResponse(
                    responseCode = "200", 
                    description = "User successfully created",
                    content = @Content(
                        mediaType = "application/json",
                        schema = @Schema(implementation = UserResponse.class)
                    )
                ),
                @ApiResponse(responseCode = "400",
                			 description = "Bad Request",
                			 content = @Content(
                				mediaType = "application/json",
                				schema = @Schema(example = "{mensaje: Formato de contraseña inválido}")))
            }
        )
    public ResponseEntity<?> registerUser(@RequestBody UserRequest usuarioReq) {
        try {
        	
            UserResponse savedUser = userService.saveUser(usuarioReq); //ejecutamos la funcion saveUser que recibe el request con la informacion del usuario y devuelve el response con la creacion del usuario 
            return ResponseEntity.ok(savedUser); //respondemos 200 y devovlemos el response
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{\"mensaje\": \"" + e.getMessage() + "\"}"); //en caso de error devolvemos error 400 y entregamos el mensaje del error
        }
    }
}

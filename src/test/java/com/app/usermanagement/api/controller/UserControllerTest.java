package com.app.usermanagement.api.controller;

import com.app.usermanagement.api.dto.UserRequest;
import com.app.usermanagement.api.dto.UserResponse;
import com.app.usermanagement.api.model.Phone;
import com.app.usermanagement.api.model.UserProfile;
import com.app.usermanagement.api.service.UserService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class UserControllerTest {

    @InjectMocks
    private UserController userController;

    @Mock
    private UserService userService;

    public UserControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser_Success() {
        UserRequest userRequest = new UserRequest();
        userRequest.setId(UUID.randomUUID());
        userRequest.setName("John Doe");
        userRequest.setEmail("john.doe@example.com");
        userRequest.setPassword("SecurePassword123");
        
        Phone phone = new Phone();
        phone.setNumber("123-456-7890");
        phone.setCityCode("01");
        phone.setCountryCode("US");
        
        userRequest.setPhones(Collections.singletonList(phone));
        
        
        UserProfile userProfile = new UserProfile();
        
        userProfile.setCreated(LocalDateTime.now());
        userProfile.setModified(LocalDateTime.now());
        userProfile.setLastLogin(LocalDateTime.now());
        userProfile.setActive(true);

        UserResponse userResponse = new UserResponse();
        userResponse.setId(userProfile.getId());
        userResponse.setName(userProfile.getName());
        userResponse.setEmail(userProfile.getEmail());
        userResponse.setCreated(userProfile.getCreated());
        userResponse.setModified(userProfile.getModified());
        userResponse.setLastLogin(userProfile.getLastLogin());
        userResponse.setToken("dummy-token");
        userResponse.setActive(userProfile.isActive());
        userResponse.setPhones(userProfile.getPhones());

        when(userService.saveUser(userRequest)).thenReturn(userResponse);

        ResponseEntity<?> response = userController.registerUser(userRequest);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(userResponse, response.getBody());
    }

    @Test
    public void testRegisterUser_Error() {
        UserRequest userRequest = new UserRequest();
        userRequest.setEmail("invalid-email");

        when(userService.saveUser(userRequest)).thenThrow(new RuntimeException("Formato de correo inválido"));

        ResponseEntity<?> response = userController.registerUser(userRequest);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("{\"mensaje\": \"Formato de correo inválido\"}", response.getBody());
    }
}

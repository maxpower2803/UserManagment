package com.app.usermanagement.api.service;

import com.app.usermanagement.api.dto.UserRequest;
import com.app.usermanagement.api.dto.UserResponse;

import com.app.usermanagement.api.model.Phone;
import com.app.usermanagement.api.model.UserProfile;
import com.app.usermanagement.api.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;
	
	@Test
	public void testSaveUser_Success() {
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
		
		

		when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.empty());

		UserResponse userResponse = userService.saveUser(userRequest);
		
		System.out.println(userResponse.toString());
		
		assertNotNull(userResponse.getId());
		assertNotNull(userResponse.getName());
		assertNotNull(userResponse.getCreated());
		assertNotNull(userResponse.getModified());
		assertNotNull(userResponse.isActive());
		assertNotNull(userResponse.getLastLogin());
		assertNotNull(userResponse.getEmail());
		assertNotNull(userResponse.getPhones());
				
	}

	@Test
	public void testSaveUser_EmailInvalid() {
		
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("invalidemail");
		userRequest.setPassword("SecurePassword123");

		RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
			userService.saveUser(userRequest);
		});
		

		assertEquals("Formato de correo inválido", thrown.getMessage());
	}

	@Test
	public void testSaveUser_PasswordInvalid() {
		UserRequest userRequest = new UserRequest();
		userRequest.setEmail("john.doe@example.com");
		userRequest.setPassword("short");

		when(userRepository.findByEmail(userRequest.getEmail())).thenReturn(Optional.empty());

		RuntimeException thrown = org.junit.jupiter.api.Assertions.assertThrows(RuntimeException.class, () -> {
			userService.saveUser(userRequest);
		});

		assertEquals("Formato de contraseña inválido", thrown.getMessage());
	}
}

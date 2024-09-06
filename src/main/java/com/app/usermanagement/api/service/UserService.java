package com.app.usermanagement.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.usermanagement.api.dto.UserRequest;
import com.app.usermanagement.api.dto.UserResponse;
import com.app.usermanagement.api.model.Phone;
import com.app.usermanagement.api.model.UserProfile;
import com.app.usermanagement.api.repository.UserRepository;

import io.jsonwebtoken.Jwts;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.crypto.SecretKey;

/**
 * Servicio para la gestión de usuarios.
 */
@Service
@Data
public class UserService {
	
    @Value("${password.regex}")
    public String passwordRegex; // expresion regular para validar la contraseña

    @Value("${email.regex}")
    public String emailRegex;// expresion regular para validar el email

	@Autowired
	private UserRepository userRepository; //inyectamos el repositorio para ejecutar comandos a la base de datos
	
	public UserResponse saveUser(UserRequest user) {
		
		Optional<UserProfile> existingUser = userRepository.findByEmail(user.getEmail());
	    if (existingUser.isPresent()) {
	        throw new RuntimeException("El correo ya registrado"); //validamos si el email ya se encuentra creado en la base de datos 
	    }

	    // Validar correo
	    if (!Pattern.matches(emailRegex, user.getEmail())) {
	        throw new RuntimeException("Formato de correo inválido"); //validamos si el email esta bien formado
	    }

	    // Validar clave
	    if (!Pattern.matches(passwordRegex, user.getPassword())) {
	        throw new RuntimeException("Formato de contraseña inválido"); //validamos si la password esta bien formado
	    }
	    
	    UserProfile userProfile = new UserProfile(); // instanciamos la clase userprofile que contiene los atributos que se guardaran en la base de datos

	    userProfile.setId(user.getId()); // Generar UUID para el ID
	    userProfile.setName(user.getName());
	    userProfile.setEmail(user.getEmail());
	    userProfile.setCreated(LocalDateTime.now());
	    userProfile.setModified(LocalDateTime.now());
	    userProfile.setLastLogin(LocalDateTime.now());
        
        String token = generateToken(user.getEmail());
        userProfile.setToken(token);
        
        // rellenamos el objeto userprofile con los datos del request y luego llamamos al repositorio para guardar los datos
        userRepository.save(userProfile);

        UserResponse response = new UserResponse(); //instanciamos el objeto de response y lo rellenamos con los datos del userprofile
        response.setId(userProfile.getId());
        response.setName(userProfile.getName());
        response.setEmail(userProfile.getEmail());
        response.setCreated(userProfile.getCreated());
        response.setModified(userProfile.getModified());
        response.setLastLogin(userProfile.getLastLogin() != null ? userProfile.getLastLogin() : userProfile.getCreated());
        response.setToken(generateToken(userProfile.getEmail()));
        response.setActive(userProfile.isActive());
        
        
        List<Phone> phones = user.getPhones();
        
        response.setPhones(phones);

        return response; //respondemos con el objeto response.

	}
	
	//funcion para generar un token en base al mail del usuario.
	public String generateToken(String email) {
		
		SecretKey key = Jwts.SIG.HS256.key().build();
		
		return Jwts.builder()
				.subject(email)
				.signWith(key)
				.compact();
	}
}

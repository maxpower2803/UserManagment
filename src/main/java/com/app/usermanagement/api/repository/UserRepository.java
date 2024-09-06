package com.app.usermanagement.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.usermanagement.api.model.UserProfile;

import java.util.Optional;
import java.util.UUID;


// repositorio para realizar operaciones con la base de datos.

public interface UserRepository extends JpaRepository<UserProfile, UUID> {
    Optional<UserProfile> findByEmail(String email);
}

package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepositoryJpa extends JpaRepository<User, UUID> {
    Optional<User> findByEmailOrUserName(String email, String userName);
}

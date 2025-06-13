package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRespositoryJpa extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}

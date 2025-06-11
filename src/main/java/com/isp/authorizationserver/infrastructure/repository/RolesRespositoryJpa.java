package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RolesRespositoryJpa extends JpaRepository<Role, Integer> {}

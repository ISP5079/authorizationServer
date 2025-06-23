package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.model.RoleClientAllowedId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleClientAllowedRepositoryJpa extends JpaRepository<RoleClientAllowed, RoleClientAllowedId> {
}

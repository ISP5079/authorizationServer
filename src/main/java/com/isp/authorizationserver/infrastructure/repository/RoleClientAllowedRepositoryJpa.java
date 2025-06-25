package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.model.RoleClientAllowedId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface RoleClientAllowedRepositoryJpa extends JpaRepository<RoleClientAllowed, RoleClientAllowedId> {
    Set<RoleClientAllowed> findByIdClient_ClientId(String roleClientId);
}

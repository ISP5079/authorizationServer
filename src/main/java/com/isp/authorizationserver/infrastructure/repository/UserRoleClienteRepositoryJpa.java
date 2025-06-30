package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.model.UserRoleClient;
import com.isp.authorizationserver.domain.model.UserRoleClientId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRoleClienteRepositoryJpa extends JpaRepository<UserRoleClient, UserRoleClientId> {
    Optional<UserRoleClient> findByIdUserAndRoleClientAllowed_IdClient_ClientId(User idUser, String clientId);
}

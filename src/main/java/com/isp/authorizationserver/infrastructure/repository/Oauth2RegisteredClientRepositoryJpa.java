package com.isp.authorizationserver.infrastructure.repository;

import com.isp.authorizationserver.domain.model.Oauth2RegisteredClient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface Oauth2RegisteredClientRepositoryJpa extends JpaRepository<Oauth2RegisteredClient, String> {
    Optional<Oauth2RegisteredClient> findByClientId(String clientId);
}

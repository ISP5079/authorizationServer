package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.ClientNotFoundException;
import com.isp.authorizationserver.domain.model.Oauth2RegisteredClient;
import com.isp.authorizationserver.domain.port.out.Oauth2RegisteredClientRepository;
import com.isp.authorizationserver.infrastructure.repository.Oauth2RegisteredClientRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class Oauth2RegisteredClientImpl implements Oauth2RegisteredClientRepository {

    private final Oauth2RegisteredClientRepositoryJpa oauth2RegisteredClientRepositoryJpa;

    public Oauth2RegisteredClientImpl(Oauth2RegisteredClientRepositoryJpa oauth2RegisteredClientRepositoryJpa) {
        this.oauth2RegisteredClientRepositoryJpa = oauth2RegisteredClientRepositoryJpa;
    }

    @Override
    public Oauth2RegisteredClient findByClientId(String clientId) {
        return oauth2RegisteredClientRepositoryJpa.findByClientId(clientId).orElseThrow(ClientNotFoundException::new);
    }
}

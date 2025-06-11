package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.ClientRq;
import com.isp.authorizationserver.adapter.dto.out.ClientRp;
import com.isp.authorizationserver.domain.exception.CreateClientException;
import com.isp.authorizationserver.domain.port.in.ClientsService;
import com.isp.authorizationserver.shared.CreateClientSecret;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientsServiceImpl implements ClientsService {

    private final RegisteredClientRepository clients;
    private final CreateClientSecret clientSecret;

    public ClientsServiceImpl(RegisteredClientRepository clients, CreateClientSecret clientSecret) {
        this.clients = clients;
        this.clientSecret = clientSecret;
    }

    @Override
    public ClientRp createClient(ClientRq clientRq) {
        try {
            RegisteredClient client =
                    RegisteredClient.withId(UUID.randomUUID().toString())
                            .clientId(clientRq.getClientId())
                            .clientSecret(clientSecret.createSecret())
                            .authorizationGrantType(AuthorizationGrantType.CLIENT_CREDENTIALS)
                            .scopes(s -> s.addAll(clientRq.getScopes()))
                            .tokenSettings(TokenSettings.builder().build())
                            .clientSettings(ClientSettings.builder().build())
                            .build();
            clients.save(client);
        } catch (Exception e) {
            throw new CreateClientException(e.getCause());
        }

        return new ClientRp(HttpStatus.CREATED.value(), "Client created");
    }
}

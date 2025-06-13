package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.ClientRq;
import com.isp.authorizationserver.adapter.dto.out.ClientRp;
import com.isp.authorizationserver.domain.exception.ClientCreateException;
import com.isp.authorizationserver.domain.port.in.ClientService;
import com.isp.authorizationserver.shared.constants.Messages;
import com.isp.authorizationserver.shared.security.CreateClientSecret;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClient;
import org.springframework.security.oauth2.server.authorization.client.RegisteredClientRepository;
import org.springframework.security.oauth2.server.authorization.settings.ClientSettings;
import org.springframework.security.oauth2.server.authorization.settings.TokenSettings;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    private final RegisteredClientRepository clients;
    private final CreateClientSecret clientSecret;

    public ClientServiceImpl(RegisteredClientRepository clients, CreateClientSecret clientSecret) {
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
        } catch (Exception ex) {
            throw new ClientCreateException(HttpStatus.CREATED, ex.getCause());
        }

        return ClientRp.builder()
                .status(HttpStatus.CONTINUE.value())
                .message(Messages.CLIENT_CREATED).build();
    }
}

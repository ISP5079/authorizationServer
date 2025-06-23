package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.Oauth2RegisteredClient;

public interface Oauth2RegisteredClientRepository {
    Oauth2RegisteredClient findByClientId(String clientId);
}
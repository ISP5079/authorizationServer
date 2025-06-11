package com.isp.authorizationserver.adapter.dto.in;

import java.util.Set;

public class ClientRq {
    private String clientId;
    private Set<String> scopes;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Set<String> getScopes() {
        return scopes;
    }

    public void setScopes(Set<String> scopes) {
        this.scopes = scopes;
    }
}

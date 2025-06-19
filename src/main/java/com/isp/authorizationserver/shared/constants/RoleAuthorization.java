package com.isp.authorizationserver.shared.constants;

import lombok.Getter;

@Getter
public enum RoleAuthorization {
    ADMIN_AUTH_SERVER("SCOPE_ADMIN_AUTH_SERVER"),
    APP("SCOPE_APP");

    private final String scope;

    RoleAuthorization(String scope) {
        this.scope = scope;
    }
}

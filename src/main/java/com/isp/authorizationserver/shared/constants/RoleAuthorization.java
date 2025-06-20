package com.isp.authorizationserver.shared.constants;

import lombok.Getter;

@Getter
public enum RoleAuthorization {
    ADMIN_AUTH_SERVER("ADMIN_AUTH_SERVER"),
    APP("APP");

    public static final String SCOPE_PREFIX = "SCOPE_";

    private final String scope;
    private final String scopeWithPrefix;

    RoleAuthorization(String scope) {
        this.scope = scope;
        this.scopeWithPrefix = SCOPE_PREFIX + scope;
    }
}

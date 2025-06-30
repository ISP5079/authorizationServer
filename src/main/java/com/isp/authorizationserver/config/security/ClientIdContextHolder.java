package com.isp.authorizationserver.config.security;

public class ClientIdContextHolder {

    private static final ThreadLocal<String> CLIENTID = new ThreadLocal<>();

    private ClientIdContextHolder() {
    }

    public static String getClientId() {
        return CLIENTID.get();
    }

    public static void setClientId(String clientId) {
        CLIENTID.set(clientId);
    }

    public static void clear() {
        CLIENTID.remove();
    }
}

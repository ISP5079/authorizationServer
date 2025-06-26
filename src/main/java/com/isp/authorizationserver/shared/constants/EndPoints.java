package com.isp.authorizationserver.shared.constants;

@SuppressWarnings("java:S1075")
public class EndPoints {

    public static final String AUTHORIZATION_SERVER = "/oauth2/**";

    public static final String CLIENT = "/client";

    public static final String ROLE = "/role";
    public static final String ROLE_APP = "/app";

    public static final String USER = "/user";
    private EndPoints() {
    }
}

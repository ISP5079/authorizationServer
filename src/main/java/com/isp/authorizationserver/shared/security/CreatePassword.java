package com.isp.authorizationserver.shared.security;

import java.security.SecureRandom;
import java.util.Base64;

public final class CreatePassword {

    public static final SecureRandom random = new SecureRandom();

    private CreatePassword() {}

    public static String generarContrasena() {
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}

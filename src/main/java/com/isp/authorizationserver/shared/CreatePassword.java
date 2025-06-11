package com.isp.authorizationserver.shared;

import java.util.Base64;
import java.util.random.RandomGenerator;

public class CreatePassword {

    private CreatePassword() {}

    public static String generarContrasena() {
        RandomGenerator random = RandomGenerator.of("SecureRandom");
        byte[] bytes = new byte[32];
        random.nextBytes(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }
}

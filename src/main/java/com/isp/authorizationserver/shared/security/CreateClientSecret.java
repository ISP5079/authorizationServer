package com.isp.authorizationserver.shared.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CreateClientSecret {
    private static final Logger log = LoggerFactory.getLogger(CreateClientSecret.class);

    private final PasswordEncoder passwordEncoder;

    public CreateClientSecret(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public String createSecret() {
        String password = CreatePassword.generarContrasena();
        log.info("password: {}", password);
        String secret = passwordEncoder.encode(password);
        log.info("secret: {} ", secret);
        return secret;
    }
}

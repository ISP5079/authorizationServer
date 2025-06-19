package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.User;

import java.util.Optional;

public interface UserRepository {
    boolean existsByEmail(String email);

    boolean existsByUsername(String username);

    Optional<User> findByEmailOrUsername(String email, String username);
    User save(User user);
}

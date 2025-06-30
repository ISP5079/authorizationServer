package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.User;

import java.util.UUID;

public interface UserRepository {
    User findByEmailOrUsername(String emailOrUserName);

    User findById(UUID uuid);
    User save(User user);
}

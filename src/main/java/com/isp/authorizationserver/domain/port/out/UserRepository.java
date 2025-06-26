package com.isp.authorizationserver.domain.port.out;

import com.isp.authorizationserver.domain.model.User;

public interface UserRepository {
    User findByEmailOrUsername(String emailOrUserName);
    User save(User user);
}

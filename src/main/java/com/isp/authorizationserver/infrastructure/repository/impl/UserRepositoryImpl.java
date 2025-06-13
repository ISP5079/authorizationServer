package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.infrastructure.repository.UserRepositoryJpa;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }
}

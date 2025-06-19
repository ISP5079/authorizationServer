package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.infrastructure.repository.UserRepositoryJpa;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public boolean existsByEmail(String email) {
        return userRepositoryJpa.existsByEmail(email);
    }

    @Override
    public boolean existsByUsername(String username) {
        return userRepositoryJpa.existsByUserName(username);
    }

    @Override
    public Optional<User> findByEmailOrUsername(String email, String username) {
        return userRepositoryJpa.findByEmailOrUserName(email, username);
    }

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }
}

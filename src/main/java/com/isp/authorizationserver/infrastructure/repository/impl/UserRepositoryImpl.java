package com.isp.authorizationserver.infrastructure.repository.impl;

import com.isp.authorizationserver.domain.exception.UserCreateException;
import com.isp.authorizationserver.domain.exception.UserNotFoundException;
import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.infrastructure.repository.UserRepositoryJpa;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryImpl(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public User findByEmailOrUsername(String emailOrUserName) {
        return userRepositoryJpa.findByEmailOrUserName(emailOrUserName, emailOrUserName).orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User save(User user) {
        try {
            return userRepositoryJpa.save(user);
        } catch (DataIntegrityViolationException ex) {
            throw new UserCreateException(HttpStatus.CONFLICT, ex.getCause());
        }
    }
}

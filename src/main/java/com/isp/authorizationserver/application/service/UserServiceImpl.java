package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.adapter.mapper.UserMapper;
import com.isp.authorizationserver.domain.exception.UserCreateException;
import com.isp.authorizationserver.domain.exception.UserNotAvailableException;
import com.isp.authorizationserver.domain.exception.UserNotFoundException;
import com.isp.authorizationserver.domain.port.in.UserService;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRp isUserExist(FindUserRq isExistUserRq) {
        Optional.ofNullable(isExistUserRq.getEmail()).ifPresentOrElse(
                email -> {
                    if (userRepository.existsByEmail(email))
                        throw new UserNotAvailableException();
                },
                () -> Optional.ofNullable(isExistUserRq.getUsername()).ifPresent(username -> {
                    if (userRepository.existsByUsername(username))
                        throw new UserNotAvailableException();
                })
        );

        return UserRp.builder()
                .status(HttpStatus.OK.value())
                .message(Messages.USER_AVAILABLE)
                .build();
    }

    @Override
    public UserRp getUser(FindUserRq findUserRq) {
        return userRepository.findByEmailOrUsername(findUserRq.getEmail(), findUserRq.getUsername())
                .map(userMapper::toUserRpByUser)
                .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public UserRp createUser(CreateUserRq userRq) {
        try {
            userRepository.save(userMapper.toUserByUserRq(userRq).toBuilder()
                    .passwordHash(passwordEncoder.encode(userRq.getPassword()))
                    .build());

            return UserRp.builder()
                    .status(HttpStatus.CREATED.value())
                    .message(Messages.USER_CREATED)
                    .build();
        } catch (DataIntegrityViolationException ex) {
            throw new UserCreateException(HttpStatus.CONFLICT, ex.getCause());
        }
    }
}

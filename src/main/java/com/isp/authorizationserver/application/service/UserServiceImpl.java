package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.adapter.mapper.UserMapper;
import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.port.in.user.UserGetInfoService;
import com.isp.authorizationserver.domain.port.in.user.UserSaveService;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserGetInfoService, UserSaveService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserRp getUser(FindUserRq findUserRq) {
        UserRp userRp = userMapper.toUserRpByUser(userRepository.findByEmailOrUsername(findUserRq.getEmailOrUsername()));

        return userRp.toBuilder()
                .status(HttpStatus.OK.value())
                .message(Messages.USER_AVAILABLE)
                .build();
    }

    @Override
    public User createUser(CreateUserRq userRq) {
        return userRepository.save(userMapper.toUserByCreateUserRq(userRq).toBuilder()
                .id(UUID.randomUUID())
                .passwordHash(passwordEncoder.encode(userRq.getPassword()))
                .passwordExpiresAt(OffsetDateTime.now().plusDays(120))
                .createdAt(OffsetDateTime.now())
                .updatedAt(OffsetDateTime.now())
                .isActive(true)
                .isVerified(false)
                .build());
    }
}

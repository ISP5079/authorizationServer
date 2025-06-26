package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.in.user.FindUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.adapter.mapper.UserMapper;
import com.isp.authorizationserver.domain.port.in.UserService;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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
    public UserRp getUser(FindUserRq findUserRq) {
        UserRp userRp = userMapper.toUserRpByUser(userRepository.findByEmailOrUsername(findUserRq.getEmailOrUsername()));

        return userRp.toBuilder()
                .status(HttpStatus.OK.value())
                .message(Messages.USER_AVAILABLE)
                .build();
    }

    @Override
    public UserRp createUser(CreateUserRq userRq) {
        userRepository.save(userMapper.toUserByCreateUserRq(userRq).toBuilder()
                .passwordHash(passwordEncoder.encode(userRq.getPassword()))
                .build());

        return UserRp.builder()
                .status(HttpStatus.CREATED.value())
                .message(Messages.USER_CREATED)
                .build();
    }
}

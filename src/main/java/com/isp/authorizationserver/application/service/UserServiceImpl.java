package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.UserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.adapter.mapper.UserMapper;
import com.isp.authorizationserver.domain.exception.UserCreateException;
import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.port.in.UserService;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public final UserRepository userRepository;
    public final UserMapper userMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserRp createUser(UserRq userRq) {
        try {
            User user = userMapper.toUserByUserRq(userRq);
            userRepository.save(user.toBuilder().build());

            return UserRp.builder()
                    .status(HttpStatus.CREATED.value())
                    .message(Messages.USER_CREATED).build();
        } catch (DataIntegrityViolationException ex) {
            throw new UserCreateException(HttpStatus.CONFLICT, ex.getCause());
        }
    }
}

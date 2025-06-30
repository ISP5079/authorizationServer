package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.user.CreateUserRq;
import com.isp.authorizationserver.adapter.dto.out.UserRp;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.model.User;
import com.isp.authorizationserver.domain.model.UserRoleClient;
import com.isp.authorizationserver.domain.model.UserRoleClientId;
import com.isp.authorizationserver.domain.port.in.UserRoleClientService;
import com.isp.authorizationserver.domain.port.in.user.UserSaveService;
import com.isp.authorizationserver.domain.port.out.RoleClientAllowedRepository;
import com.isp.authorizationserver.domain.port.out.UserRepository;
import com.isp.authorizationserver.domain.port.out.UserRoleClientRepository;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleClientServiceImpl implements UserRoleClientService {

    private final UserSaveService userSaveService;
    private final RoleClientAllowedRepository roleClientAllowedRepository;
    private final UserRepository userRepository;
    private final UserRoleClientRepository userRoleClientRepository;

    public UserRoleClientServiceImpl(UserSaveService userSaveService, RoleClientAllowedRepository roleClientAllowedRepository, UserRepository userRepository, UserRoleClientRepository userRoleClientRepository) {
        this.userSaveService = userSaveService;
        this.roleClientAllowedRepository = roleClientAllowedRepository;
        this.userRepository = userRepository;
        this.userRoleClientRepository = userRoleClientRepository;
    }

    @Override
    @Transactional
    public UserRp registerUser(CreateUserRq userRq) {
        RoleClientAllowed roleClientAllowed = roleClientAllowedRepository.findRoleAppByAppAndRole(userRq.getAppName(), userRq.getRoles().getFirst());
        User user = Optional.ofNullable(userRq.getIdUser())
                .map(userRepository::findById)
                .orElseGet(() -> userSaveService.createUser(userRq));

        userRoleClientRepository.save(UserRoleClient.builder()
                .id(UserRoleClientId.builder()
                        .idUser(user.getId())
                        .idRole(roleClientAllowed.getId().getIdRole())
                        .idClient(roleClientAllowed.getId().getIdClient())
                        .build())
                .idUser(user)
                .roleClientAllowed(roleClientAllowed)
                .build());
        return UserRp.builder().status(HttpStatus.CREATED.value()).build();
    }
}

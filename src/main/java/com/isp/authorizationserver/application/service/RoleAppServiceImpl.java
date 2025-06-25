package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.adapter.mapper.RoleMapper;
import com.isp.authorizationserver.domain.exception.RoleNotFoundException;
import com.isp.authorizationserver.domain.model.Oauth2RegisteredClient;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.model.RoleClientAllowedId;
import com.isp.authorizationserver.domain.port.in.RoleAppService;
import com.isp.authorizationserver.domain.port.out.Oauth2RegisteredClientRepository;
import com.isp.authorizationserver.domain.port.out.RoleClientAllowedRepository;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleAppServiceImpl implements RoleAppService {

    private static final String GET_ALL_ROLES = "all";
    private static final String JWT_SUBJECT_CLIENT_ID = "sub";

    private final RoleClientAllowedRepository roleClientAllowedRepository;
    private final RoleRepository roleRepository;
    private final Oauth2RegisteredClientRepository oauth2RegisteredClientRepository;
    private final RoleMapper roleMapper;

    public RoleAppServiceImpl(RoleClientAllowedRepository roleClientAllowedRepository, RoleRepository roleRepository, Oauth2RegisteredClientRepository oauth2RegisteredClientRepository, RoleMapper roleMapper) {
        this.roleClientAllowedRepository = roleClientAllowedRepository;
        this.roleRepository = roleRepository;
        this.oauth2RegisteredClientRepository = oauth2RegisteredClientRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleRp createRoleApp(CreateRoleAppRq createRoleApp) {
        Role role = Optional.ofNullable(createRoleApp.getIdRole()).map(roleRepository::findById)
                .orElseGet(() -> roleRepository.findByName(createRoleApp.getRoleName()));

        Oauth2RegisteredClient client = oauth2RegisteredClientRepository.findByClientId(createRoleApp.getAppName());

        roleClientAllowedRepository.save(
                RoleClientAllowed.builder()
                        .id(RoleClientAllowedId.builder()
                                .idRole(role.getId())
                                .idClient(client.getId())
                                .build())
                        .idRole(role)
                        .idClient(client)
                        .build()
        );

        return RoleRp.builder().status(HttpStatus.CREATED.value()).build();
    }

    @Override
    public RoleRp findRoleAppByApp(String appName) {
        if (appName.equals(GET_ALL_ROLES)) {
            return RoleRp.builder()
                    .status(HttpStatus.OK.value())
                    .roles(roleClientAllowedRepository.findAllRoleApp().stream()
                            .map(roleMapper::toRoleRpByRoleClientAllowed)
                            .collect(Collectors.toSet()))
                    .build();
        }

        return RoleRp.builder()
                .status(HttpStatus.OK.value())
                .roles(roleClientAllowedRepository.findRoleAppByApp(appName).stream()
                        .map(roleMapper::toRoleRpByRoleClientAllowed)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public RoleRp findRoleAppByApp(Jwt jwt) {
        String appName = Optional.ofNullable(jwt.getClaimAsString(JWT_SUBJECT_CLIENT_ID)).orElseThrow(() -> new RoleNotFoundException(RoleNotFoundException.ROLES_NOT_FOUND));
        return this.findRoleAppByApp(appName);
    }
}

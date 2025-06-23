package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.model.Oauth2RegisteredClient;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import com.isp.authorizationserver.domain.model.RoleClientAllowedId;
import com.isp.authorizationserver.domain.port.in.RoleAppService;
import com.isp.authorizationserver.domain.port.out.Oauth2RegisteredClientRepository;
import com.isp.authorizationserver.domain.port.out.RoleClientAllowedRepository;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleAppServiceImpl implements RoleAppService {

    private final RoleClientAllowedRepository roleClientAllowedRepository;
    private final RoleRepository roleRepository;
    private final Oauth2RegisteredClientRepository oauth2RegisteredClientRepository;

    public RoleAppServiceImpl(RoleClientAllowedRepository roleClientAllowedRepository, RoleRepository roleRepository, Oauth2RegisteredClientRepository oauth2RegisteredClientRepository) {
        this.roleClientAllowedRepository = roleClientAllowedRepository;
        this.roleRepository = roleRepository;
        this.oauth2RegisteredClientRepository = oauth2RegisteredClientRepository;
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
                        .client(client)
                        .build()
        );

        return RoleRp.builder().status(HttpStatus.CREATED.value()).build();
    }
}

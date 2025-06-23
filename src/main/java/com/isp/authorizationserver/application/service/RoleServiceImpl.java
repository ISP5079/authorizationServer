package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.adapter.mapper.RoleMapper;
import com.isp.authorizationserver.domain.exception.RoleNotFoundException;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.port.in.RoleService;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService {

    private final RoleRepository rolesRepository;
    private final RoleMapper roleMapper;

    public RoleServiceImpl(RoleRepository rolesRepository, RoleMapper roleMapper) {
        this.rolesRepository = rolesRepository;
        this.roleMapper = roleMapper;
    }

    @Override
    public RoleRp createRole(CreateRoleRq roleRq) {
        rolesRepository.save(
                Role.builder()
                        .name(roleRq.getName())
                        .description(roleRq.getDescription())
                        .build());

        return RoleRp.builder()
                .status(HttpStatus.CREATED.value())
                .message(Messages.ROLE_CREATED).build();
    }

    @Override
    public RoleRp getRoles() {
        Set<Role> roles = rolesRepository.findAll();

        if (roles.isEmpty())
            throw new RoleNotFoundException(RoleNotFoundException.ROLES_NOT_FOUND);

        return RoleRp.builder()
                .status(HttpStatus.OK.value())
                .roles(roles.stream()
                        .map(roleMapper::toRoleRpByRole)
                        .collect(Collectors.toSet()))
                .build();
    }

    @Override
    public RoleRp getRoleByRoleName(String roleName) {
        return roleMapper.toRoleRpByRole(
                        rolesRepository.findByName(
                                roleName.toUpperCase()))
                .toBuilder().status(HttpStatus.OK.value())
                .build();
    }
}

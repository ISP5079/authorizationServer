package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.RoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.adapter.mapper.RoleMapper;
import com.isp.authorizationserver.domain.exception.RoleCreateException;
import com.isp.authorizationserver.domain.exception.RoleNotFoundException;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.port.in.RoleService;
import com.isp.authorizationserver.domain.port.out.RoleRepository;
import com.isp.authorizationserver.shared.constants.Messages;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

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
    public RoleRp createRole(RoleRq roleRq) {
        try{
            rolesRepository.save(
                    Role.builder()
                            .name(roleRq.getName())
                            .description(roleRq.getDescription())
                            .build());

            return RoleRp.builder()
                    .status(HttpStatus.CREATED.value())
                    .message(Messages.ROLE_CREATED).build();
        }catch (DataIntegrityViolationException e){
            throw new RoleCreateException(HttpStatus.CONFLICT, e.getCause());
        }
    }

    @Override
    public RoleRp getRoleByRoleName(String roleName) {
        return rolesRepository.findByName(roleName.toUpperCase())
                .map(roleMapper::toUserRp)
                .orElseThrow(RoleNotFoundException::new);
    }
}

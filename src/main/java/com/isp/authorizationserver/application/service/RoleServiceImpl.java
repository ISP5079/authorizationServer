package com.isp.authorizationserver.application.service;

import com.isp.authorizationserver.adapter.dto.in.RoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.exception.CreateRoleException;
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

    public RoleServiceImpl(RoleRepository rolesRepository) {
        this.rolesRepository = rolesRepository;
    }

    @Override
    public RoleRp createRole(RoleRq roleRq) {
        try{
            Role role = rolesRepository.save(
                    Role.builder()
                            .name(roleRq.getName())
                            .description(roleRq.getDescription())
                            .build());

            return new RoleRp(HttpStatus.CREATED, Messages.ROLE_CREATED, role.getId());
        }catch (DataIntegrityViolationException e){
            throw new CreateRoleException(HttpStatus.CONFLICT, e.getCause());
        }
    }
}

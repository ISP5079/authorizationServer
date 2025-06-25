package com.isp.authorizationserver.adapter.mapper;

import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.model.Role;
import com.isp.authorizationserver.domain.model.RoleClientAllowed;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "idRole", source = "id")
    @Mapping(target = "roleName", source = "name")
    RoleRp toRoleRpByRole(Role role);

    @Mapping(target = "idRole", ignore = true)
    @Mapping(target = "roleName", source = "idRole.name")
    @Mapping(target = "app", source = "idClient.clientId")
    RoleRp toRoleRpByRoleClientAllowed(RoleClientAllowed roleClientAllowed);
}

package com.isp.authorizationserver.adapter.mapper;

import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.model.Role;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    @Mapping(target = "idRole", source = "id")
    @Mapping(target = "roleName", source = "name")
    RoleRp toUserRp(Role role);
}

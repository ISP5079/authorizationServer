package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.port.in.RoleAppService;
import com.isp.authorizationserver.domain.port.in.RoleService;
import com.isp.authorizationserver.shared.constants.EndPoints;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.ROLE)
public class RoleController {
    private final RoleService roleService;
    private final RoleAppService roleAppService;

    public RoleController(RoleService roleService, RoleAppService roleAppService) {
        this.roleService = roleService;
        this.roleAppService = roleAppService;
    }

    @PostMapping
    public ResponseEntity<RoleRp> createRole(@Valid @RequestBody CreateRoleRq roleRq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRq));
    }

    @GetMapping
    public ResponseEntity<RoleRp> getRoles() {
        return ResponseEntity.ok().body(roleService.getRoles());
    }

    @PreAuthorize("""
                hasAnyAuthority(
                    T(com.isp.authorizationserver.shared.constants.RoleAuthorization).ADMIN_AUTH_SERVER.getScopeWithPrefix()
                )
            """)
    @GetMapping(EndPoints.ROLE_FIND_BY_ROLE_NAME)
    public ResponseEntity<RoleRp> getRoleByRoleName(@PathVariable String roleName) {
        return ResponseEntity.ok(roleService.getRoleByRoleName(roleName));
    }

    @PreAuthorize("""
                hasAnyAuthority(
                    T(com.isp.authorizationserver.shared.constants.RoleAuthorization).ADMIN_AUTH_SERVER.getScopeWithPrefix()
                )
            """)
    @PostMapping(EndPoints.ROLE_APP)
    public ResponseEntity<RoleRp> registerRoleApp(@RequestBody @Valid CreateRoleAppRq createRoleAppRq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleAppService.createRoleApp(createRoleAppRq));
    }

    @GetMapping(EndPoints.ROLE_APP)
    public ResponseEntity<RoleRp> getRoleForApp() {
        return null;
    }

    public ResponseEntity<RoleRp> getRoleByApp() {
        return null;
    }


}

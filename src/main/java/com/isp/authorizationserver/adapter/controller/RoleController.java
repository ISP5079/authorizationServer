package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleAppRq;
import com.isp.authorizationserver.adapter.dto.in.role.CreateRoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.port.in.RoleClientService;
import com.isp.authorizationserver.domain.port.in.RoleService;
import com.isp.authorizationserver.shared.constants.EndPoints;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(EndPoints.ROLE)
public class RoleController {
    private final RoleService roleService;
    private final RoleClientService roleAppService;

    public RoleController(RoleService roleService, RoleClientService roleAppService) {
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
    @GetMapping("/{roleName}")
    public ResponseEntity<RoleRp> getRoleByRoleName(@PathVariable("roleName") String roleName) {
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

    @PreAuthorize("""
            hasAnyAuthority(T(com.isp.authorizationserver.shared.constants.RoleAuthorization).ADMIN_AUTH_SERVER.getScopeWithPrefix())
            """)
    @GetMapping(EndPoints.ROLE_APP + "/{appName}")
    public ResponseEntity<RoleRp> getRoleByApp(@PathVariable String appName) {
        return ResponseEntity.ok().body(roleAppService.findRoleAppByApp(appName));
    }

    @PreAuthorize("""
            hasAnyAuthority(T(com.isp.authorizationserver.shared.constants.RoleAuthorization).APP.getScopeWithPrefix())
            """)
    @GetMapping(EndPoints.ROLE_APP)
    public ResponseEntity<RoleRp> getRoleForApp(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok().body(roleAppService.findRoleAppByApp(jwt));
    }
}

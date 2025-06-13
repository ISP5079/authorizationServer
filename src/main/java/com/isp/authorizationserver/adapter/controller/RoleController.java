package com.isp.authorizationserver.adapter.controller;

import com.isp.authorizationserver.adapter.dto.in.RoleRq;
import com.isp.authorizationserver.adapter.dto.out.RoleRp;
import com.isp.authorizationserver.domain.port.in.RoleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/role")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<RoleRp> createRole(@Valid @RequestBody RoleRq roleRq) {
        return ResponseEntity.status(HttpStatus.CREATED).body(roleService.createRole(roleRq));
    }

    @GetMapping("/{roleName}")
    public ResponseEntity<RoleRp> getRoleByRoleName(@PathVariable String roleName) {
        return ResponseEntity.ok().body(roleService.getRoleByRoleName(roleName));
    }


}

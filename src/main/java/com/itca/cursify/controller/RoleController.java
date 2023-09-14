package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.service.RoleService;
import com.itca.cursify.service.dto.RoleInDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/roles")
public class RoleController {
    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public Role createRole(@RequestBody RoleInDTO roleInDTO) {
        return this.roleService.createRole(roleInDTO);
    }

    @GetMapping
    public List<Role> findAllRoles() {
        return this.roleService.findAllRoles();
    }
}

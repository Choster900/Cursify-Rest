package com.itca.cursify.service;

import com.itca.cursify.mapper.RoleInDTOToRole;
import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.repository.RoleRepository;
import com.itca.cursify.service.dto.RoleInDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    private final RoleInDTOToRole roleInDTOToRole;

    public RoleService(RoleRepository roleRepository, RoleInDTOToRole roleInDTOToRole, RoleInDTOToRole roleInDTOToRole1) {
        this.roleRepository = roleRepository;
        this.roleInDTOToRole = roleInDTOToRole1;
    }

    public Role createRole(RoleInDTO roleInDTO) {
        Role role = roleInDTOToRole.map(roleInDTO);
        return this.roleRepository.save(role);
    }

    public List<Role> findAllRoles() {
        return this.roleRepository.findAll();
    }
}

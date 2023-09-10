package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.RoleStatus;
import com.itca.cursify.service.dto.RoleInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class RoleInDTOToRole implements IMapper<RoleInDTO, Role> {
    @Override
    public Role map(RoleInDTO in) {
        Role role = new Role();
        role.setRoleName(in.getRoleName());
        role.setRoleStatus(RoleStatus.ENABLED);
        role.setCreatedAtRole(LocalDateTime.now());
        return role;
    }
}

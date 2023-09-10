package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public interface RoleRepository extends JpaRepository<Role, Long> {

}

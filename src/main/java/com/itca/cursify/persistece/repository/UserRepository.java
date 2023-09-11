package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // Verificar si existe un usuario con un nombre de usuario específico
    boolean existsByUserName(String userName);

    // Verificar si existe un usuario con un correo electrónico específico
    boolean existsByUserEmail(String userEmail);
}

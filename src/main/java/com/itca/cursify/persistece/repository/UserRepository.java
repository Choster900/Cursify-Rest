package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    // Encontrar un usuario por su correo electrónico
    Optional<User> findByUserEmail(String userEmail);

    // Verificar si existe un usuario con un correo electrónico específico
    boolean existsByUserEmail(String userEmail);
}

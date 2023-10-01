package com.itca.cursify.service;

import com.itca.cursify.mapper.UserInDTOToUser;
import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.dto.UserWithRoleDTO;
import com.itca.cursify.service.security.EncryptServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;
    private final EncryptServiceImpl encryptServiceImpl;

    public UserService(UserRepository userRepository, UserInDTOToUser userInDTOToUser, EncryptServiceImpl encryptServiceImpl) {
        this.userRepository = userRepository;
        this.userInDTOToUser = userInDTOToUser;
        this.encryptServiceImpl = encryptServiceImpl;
    }
    public UserWithRoleDTO createUser(UserInDTO userInDTO) {
        User user = userInDTOToUser.map(userInDTO);
        User savedUser = userRepository.save(user);

        // Aquí usamos el ID del savedUser para buscar la información completa incluyendo el rol.
        Optional<User> userOptional = userRepository.findByUserId(savedUser.getUserId());
        if (userOptional.isPresent()) {
            User userWithRole = userOptional.get();
            return new UserWithRoleDTO(
                    userWithRole.getUserId(),
                    userWithRole.getUserEmail(),
                    userWithRole.getUserName(),
                    userWithRole.getUserLastName(),
                    userWithRole.getRole()
            );
        }
        return null;
    }

    public UserWithRoleDTO findUserByEmailAndPassword(String email, String password) {
        Optional<User> userOptional = userRepository.findByUserEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String storedPasswordHash = user.getUserPassword();
            if (encryptServiceImpl.verifyPassword(password, storedPasswordHash)) {
                return new UserWithRoleDTO(
                        user.getUserId(),
                        user.getUserEmail(),
                        user.getUserName(),
                        user.getUserLastName(),
                        user.getRole()
                );
            }
        }
        return null;
    }
    public UserWithRoleDTO findUserById(Long id) {
        Optional<User> userOptional = userRepository.findByUserId(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
                return new UserWithRoleDTO(
                        user.getUserId(),
                        user.getUserEmail(),
                        user.getUserName(),
                        user.getUserLastName(),
                        user.getRole()
                );
        }
        return null;
    }


}

package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.RoleRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.security.EncryptService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class UserInDTOToUser implements IMapper<UserInDTO, User> {

    private final RoleRepository roleRepository;
    private final EncryptService encryptService;
    private final UserRepository userRepository;
    public UserInDTOToUser(RoleRepository roleRepository, EncryptService encryptService, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.encryptService = encryptService;
        this.userRepository = userRepository;
    }

    @Override
    public User map(UserInDTO in) {
        // Verificar si el correo electrónico ya existe
        if (userRepository.existsByUserEmail(in.getUserEmail())) {
            throw new IllegalArgumentException("El correo electrónico ya existe");
        }

        User user = new User();
        user.setUserName(in.getUserName());
        user.setUserLastName(in.getUserLastName());
        user.setUserEmail(in.getUserEmail());
        user.setUserDescription(in.getUserDescription());
        String userPassword = in.getUserPassword();
        String hashedPassword = encryptService.scryptPassoword(userPassword);
        user.setUserPassword(hashedPassword);

        user.setCreatedAtUser(LocalDateTime.now());

        //Enviando el rol al usuario
        Role role = roleRepository.findById(in.getRoleId())
                .orElseThrow(() -> new IllegalArgumentException("Role no encontrado con ID: " + in.getRoleId()));
        user.setRole(role);

        return user;
    }
}

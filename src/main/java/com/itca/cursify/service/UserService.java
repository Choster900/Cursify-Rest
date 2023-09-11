package com.itca.cursify.service;

import com.itca.cursify.mapper.UserInDTOToUser;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.security.EncryptServiceImpl;
import org.springframework.stereotype.Service;

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

    public User createUser(UserInDTO userInDTO) {
        User user = userInDTOToUser.map(userInDTO);
        return this.userRepository.save(user);
    }

    public User findUserByEmailAndPassword(String email, String password) {

        Optional<User> userOptional = userRepository.findByUserEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            String storedPasswordHash = user.getUserPassword();

            if (encryptServiceImpl.verifyPassword(password, storedPasswordHash)) {
                return user;
            }
        }

        return null;
    }
}

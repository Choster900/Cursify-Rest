package com.itca.cursify.service;

import com.itca.cursify.mapper.UserInDTOToUser;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.UserInDTO;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    private final UserRepository userRepository;
    private final UserInDTOToUser userInDTOToUser;

    public UserServices(UserRepository userRepository, UserInDTOToUser userInDTOToUser) {
        this.userRepository = userRepository;
        this.userInDTOToUser = userInDTOToUser;
    }
    public User createUser(UserInDTO userInDTO) {
        User user = userInDTOToUser.map(userInDTO);
        return this.userRepository.save(user);
    }
}

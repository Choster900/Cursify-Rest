package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.service.UserService;
import com.itca.cursify.service.dto.LoginRequestDTO;
import com.itca.cursify.service.dto.UserInDTO;
import com.itca.cursify.service.dto.UserWithRoleDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public User createUser(@RequestBody UserInDTO userInDTO) {
        return this.userService.createUser(userInDTO);
    }

    @PostMapping("/login")
    public UserWithRoleDTO login(@RequestBody LoginRequestDTO loginRequest) {
        String email = loginRequest.getUserEmail();
        String password = loginRequest.getUserPassword();
        return userService.findUserByEmailAndPassword(email, password);
    }

}

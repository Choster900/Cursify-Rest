package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.service.RoleService;
import com.itca.cursify.service.UserServices;
import com.itca.cursify.service.dto.RoleInDTO;
import com.itca.cursify.service.dto.UserInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    private final UserServices userServices;

    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @PostMapping("/create")
    public User createUser(@RequestBody UserInDTO userInDTO) {
        return this.userServices.createUser(userInDTO);
    }

}

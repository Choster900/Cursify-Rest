package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.User;
import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserWithRoleDTO {

    private Long userId;
    private String userEmail;
    private String userName;
    private String userLastName;
    private Role role;

}

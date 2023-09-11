package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserInDTO {
    private String userName;
    private String userLastName;
    private String userEmail;
    private String userPassword;
    private Long roleId;
}

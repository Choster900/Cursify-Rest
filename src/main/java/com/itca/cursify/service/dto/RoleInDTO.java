package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.RoleStatus;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;
@Data
@Setter
@Getter
public class RoleInDTO {

    private String roleName;  // Nombre del role

}

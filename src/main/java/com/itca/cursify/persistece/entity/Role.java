package com.itca.cursify.persistece.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long role_id;  // Corresponde a un campo de base de datos de tipo int
    @Column(name = "role_name")
    private String roleName;  // Corresponde a un campo de base de datos de tipo varchar
    private RoleStatus roleStatus;
    @Column(name = "created_at_role")
    private LocalDateTime createdAtRole;  // Corresponde a un campo de base de datos de tipo datetime

    @Column(name = "modified_at_role")
    private LocalDateTime modifiedAtRole;  // Corresponde a un campo de base de datos de tipo datetime

}

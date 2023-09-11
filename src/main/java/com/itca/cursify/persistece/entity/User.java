package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "user_lastname")
    private String userLastName;
    @Column(name = "user_email")
    private String userEmail;
    @Column(name = "user_password")
    private String userPassword;

    @ManyToOne
    @JoinColumn(name = "role_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Role role;

    @Column(name = "created_at_user")
    private LocalDateTime createdAtUser;
    @Column(name = "modified_at_user")
    private LocalDateTime modifiedAtUser;
}

package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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

    @OneToMany(mappedBy = "creatorUser", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    //Lista de cursos que el usuario a creado
    private List<Course> coursesList = new ArrayList<>();

    @Column(name = "created_at_user")
    private LocalDateTime createdAtUser;
    @Column(name = "modified_at_user")
    private LocalDateTime modifiedAtUser;

    // De muchos a muchos a tabla enrollment
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "enrollment",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            }
    )
    @JsonIgnore
    private Set<Course> enrolledCourses;
    // De muchos a muchos a tabla comment
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "comment",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "course_id", referencedColumnName = "course_id"),
            }
    )
    @JsonIgnore
    private Set<Course> commentCourse;

    @OneToMany(mappedBy = "userExamResult", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    //Lista de cursos que el usuario a creado
    private List<ExamResult> examResults = new ArrayList<>();


}

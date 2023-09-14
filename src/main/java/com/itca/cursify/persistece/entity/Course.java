package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long courseId;
    @Column(name = "course_name")
    private String courseName;
    @Column(name = "course_description")
    private String courseDescription;
    @Column(name = "course_photo")
    private String coursePhoto;
    @Column(name = "course_published")
    private Published coursePublished;

    @ManyToOne
    @JoinColumn(name = "category_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Category category;

    @Column(name = "created_at_course")
    private LocalDate createdAtCourse;
    @Column(name = "modified_at_course")
    private LocalDate modifiedAtCourse;

    @ManyToMany(mappedBy = "enrolledCourses",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> enrolledUsers;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User creatorUser;

}

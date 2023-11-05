package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
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
@Table(name = "course")
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
    private LocalDateTime createdAtCourse;
    @Column(name = "modified_at_course")
    private LocalDateTime modifiedAtCourse;

    @ManyToMany(mappedBy = "enrolledCourses",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<User> enrolledUsers;

    /*@ManyToMany(mappedBy = "commentCourse",fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comment> commentCourse;*/
   /* @ManyToMany(mappedBy = "comments", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Comment> comments;*/

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "course_id", referencedColumnName = "course_id")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User creatorUser;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Section> sections = new ArrayList<>();

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<Exam> examArrayList = new ArrayList<>();

}

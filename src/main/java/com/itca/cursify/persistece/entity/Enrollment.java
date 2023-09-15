package com.itca.cursify.persistece.entity;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.User;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "enrollment")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "enrollment_id")
    private Long enrollmentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @Column(name = "enrollment_date")
    private LocalDate enrollmentDate;

    @Column(name = "created_at_enrollment")
    private LocalDateTime createdAtEnrollment;

    @Column(name = "modified_at_enrollment")
    private LocalDateTime modifiedAtEnrollment;
}

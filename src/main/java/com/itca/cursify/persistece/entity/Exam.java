package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam")
public class Exam {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exam_id")
    private Long examId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    @JsonIgnore
    private Course course;

    @Column(name = "exam_title")
    private String examTitle;
    @Column(name = "exam_duration")
    private Integer examDuration;
    @Column(name = "created_at_exam")
    private LocalDateTime createdAtExam;
    @Column(name = "modified_at_exam")
    private LocalDateTime modifiedAtExam;

    @OneToMany(mappedBy = "exam", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    //Lista de cursos que el usuario a creado
    private List<ExamResult> examResults = new ArrayList<>();

    @OneToMany(mappedBy = "examQuestions", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    //Lista de cursos que el usuario a creado
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "examResponse", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    //Lista de cursos que el usuario a creado
    private List<AnsweredResponse> answeredResponses = new ArrayList<>();
}

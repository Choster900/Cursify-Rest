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
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;
    @Column(name = "question_text")
    private String questionText;

    @ManyToOne
    @JoinColumn(name = "exam_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Exam examQuestions;

    @Column(name = "created_at_question")
    private LocalDateTime createdAtQuestion;
    @Column(name = "modified_at_question")
    private LocalDateTime modifiedAtQuestion;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<AnswerOption> answerOptions = new ArrayList<>();

    @OneToMany(mappedBy = "questionResponse", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<AnsweredResponse> answeredResponses = new ArrayList<>();


}

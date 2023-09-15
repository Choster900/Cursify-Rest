package com.itca.cursify.persistece.entity;

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
@Table(name = "answered_response")
public class AnsweredResponse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "response_id")
    private Long responseId;

    @ManyToOne
    @JoinColumn(name = "exam_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Exam examResponse;

    @ManyToOne
    @JoinColumn(name = "question_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Question questionResponse;

    @ManyToOne
    @JoinColumn(name = "option_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private AnswerOption answerResponse;

    @Column(name = "created_at_response")
    private LocalDateTime createdAtResponse;
    @Column(name = "modified_at_response")
    private LocalDateTime modifiedAtResponse;
}

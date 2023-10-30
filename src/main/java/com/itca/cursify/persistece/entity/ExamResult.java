package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "exam_result")
public class ExamResult {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "result_id")
    private Long resultId;

    @ManyToOne
    @JoinColumn(name = "exam_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Exam exam;


    @ManyToOne
    @JoinColumn(name = "user_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private User userExamResult;

    @Column(name = "result_score")
    private Integer resultScore;
    @Column(name = "result_grade")
    private Double resultGrade;
    @Column(name = "result_completion_date")
    private Date resultCompletionDate;
    @Column(name = "created_at_result")
    private LocalDateTime createdAtResult;
    @Column(name = "modified_at_result")
    private LocalDateTime modifiedAtResult;
}

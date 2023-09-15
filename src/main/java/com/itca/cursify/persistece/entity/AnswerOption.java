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
@Table(name = "answer_option")
public class AnswerOption {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "option_id")
    private Long optionId;

    @ManyToOne
    @JoinColumn(name = "question_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Question question;

    @Column(name = "options_text")
    private String optionsText;
    @Column(name = "option_is_correct")
    private Integer optionIsCorrect;
    @Column(name = "created_at_option")
    private LocalDateTime createdAtOption;
    @Column(name = "modified_at_option")
    private LocalDateTime modifiedAtOption;

    @OneToMany(mappedBy = "answerResponse", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<AnsweredResponse> answeredResponses = new ArrayList<>();

}

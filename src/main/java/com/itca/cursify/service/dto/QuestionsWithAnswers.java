package com.itca.cursify.service.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.Question;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionsWithAnswers {
    private Long questionId;
    private String questionText;
    private Exam exam;
    private List<AnswerOption> answerOptionList;
    @Data
    public static class AnswerOption{
        private Long optionId;
        private Long questionId;
        private String optionText;
        private Integer optionIsCorrect;
    }
}

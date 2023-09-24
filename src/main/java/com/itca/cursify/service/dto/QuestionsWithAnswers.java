package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.AnswerOption;
import lombok.*;

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
    private List<AnswerOption> answerOptionList;
}

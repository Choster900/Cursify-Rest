package com.itca.cursify.service.dto;

import lombok.Data;

import java.util.List;

@Data
public class QuestionWithAnswersDTO {
    private String questionText;
    private Long examId;
    private List<AnswerDTO> answers;

    @Data
    public static class AnswerDTO {
        private String optionText;
     //   private Long questionId;
        private Integer optionIsCorrect;
    }
}
package com.itca.cursify.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class AnswerOptionDTO {
    private String optionText;
    private Integer optionIsCorrect;
    private Long questionId;
}

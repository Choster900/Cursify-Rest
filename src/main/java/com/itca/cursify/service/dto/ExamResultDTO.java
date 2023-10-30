package com.itca.cursify.service.dto;

import lombok.Data;

@Data
public class ExamResultDTO {
    private Long examId;
    private Long userId;
    private Integer resultScore;
    private Double resultGrade;
}

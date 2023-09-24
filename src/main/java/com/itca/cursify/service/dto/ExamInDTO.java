package com.itca.cursify.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ExamInDTO {
    // Writing data we are going to request
    private Long courseId;
    private String examTitle;
    private Integer examDuration;
}

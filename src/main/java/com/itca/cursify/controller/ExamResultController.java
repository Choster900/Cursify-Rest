package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.ExamResult;
import com.itca.cursify.service.ExamResultService;
import com.itca.cursify.service.dto.ExamResultDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/exam-result")
public class ExamResultController {
    private final ExamResultService examResultService;

    public ExamResultController(ExamResultService examResultService) {
        this.examResultService = examResultService;
    }

    @PostMapping
    public ExamResult insert(@RequestBody ExamResultDTO examResultDTO){
        return examResultService.insertExamResult(examResultDTO);
    }
}

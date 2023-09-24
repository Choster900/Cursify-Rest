package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.service.QuestionService;
import com.itca.cursify.service.dto.ExamInDTO;
import com.itca.cursify.service.dto.QuestionInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/questions")
public class QuestionController {
    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }
    @PostMapping
    public Question createQuestion(@RequestBody QuestionInDTO questionInDTO){
        return this.questionService.createQuestionForExam(questionInDTO);
    }
}

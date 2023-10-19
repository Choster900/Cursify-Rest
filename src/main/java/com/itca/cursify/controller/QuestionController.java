package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.service.QuestionService;
import com.itca.cursify.service.dto.ExamInDTO;
import com.itca.cursify.service.dto.QuestionInDTO;
import com.itca.cursify.service.dto.QuestionWithAnswersDTO;
import org.springframework.web.bind.annotation.*;

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
    @PutMapping("/{questionId}")
    public Question updateQuestion(@RequestParam Long questionId, @RequestBody QuestionInDTO questionInDTO) {
        return this.questionService.updateQuestionForExam(questionId,questionInDTO);
    }
    @PostMapping("/withAnswers")
    public Question createQuestionWithMultipleAnswer(@RequestBody QuestionWithAnswersDTO questionWithAnswersDTO){
        return this.questionService.createQuestionMultiplesAnswer(questionWithAnswersDTO);
    }
    /*@PutMapping("/withAnswer/{questionId}")
    public Question update(@RequestParam Long questionId, @RequestBody QuestionWithAnswersDTO questionWithAnswersDTO ){
        return this.questionService.updateQuestionWithAnswers(questionId,questionWithAnswersDTO);
    }*/
}

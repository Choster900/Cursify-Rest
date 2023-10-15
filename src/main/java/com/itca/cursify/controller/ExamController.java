package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.service.ExamService;
import com.itca.cursify.service.QuestionService;
import com.itca.cursify.service.dto.ExamInDTO;
import com.itca.cursify.service.dto.QuestionsWithAnswers;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/exams")
public class ExamController {
    private final ExamService examServices;
    private final QuestionService questionServices;

    public ExamController(ExamService examServices, QuestionService questionServices) {
        this.examServices = examServices;
        this.questionServices = questionServices;
    }
    @PostMapping
    public Exam createExamForCourse(@RequestBody ExamInDTO examInDTO){
        return this.examServices.createNewExam(examInDTO);
    }
    @PutMapping("/{examId}")
    public Exam updateExamForCourse(@PathVariable Long examId, @RequestBody ExamInDTO examInDTO){
        return this.examServices.updateExam(examId, examInDTO);
    }
    @GetMapping("/byExam/{examId}")
    public ResponseEntity<List<QuestionsWithAnswers>> findQuestionsByExamId(@PathVariable Long examId) {
        List<QuestionsWithAnswers> questionsWithAnswersList = questionServices.findQuestionsByExamId(examId);

        if (questionsWithAnswersList.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(questionsWithAnswersList);
        }
    }

}

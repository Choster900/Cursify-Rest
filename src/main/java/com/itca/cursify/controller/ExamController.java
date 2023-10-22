package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.service.ExamService;
import com.itca.cursify.service.QuestionService;
import com.itca.cursify.service.dto.ExamInDTO;
import com.itca.cursify.service.dto.QuestionsWithAnswers;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public List<QuestionsWithAnswers> findQuestionsByExamId(@PathVariable Long examId) {
            return  this.questionServices.findQuestionsByExamId(examId);

    }
    @GetMapping("/getInformationExamByExamId/{examId}")
    public Optional<Exam> getByExamId(@PathVariable Long examId) {
        return this.examServices.getExamById(examId);
    }

}

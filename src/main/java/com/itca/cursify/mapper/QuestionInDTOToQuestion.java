package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.Question;
import com.itca.cursify.persistece.repository.ExamRepository;
import com.itca.cursify.service.dto.QuestionInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class QuestionInDTOToQuestion implements IMapper<QuestionInDTO, Question>{
    private final ExamRepository examRepository;

    public QuestionInDTOToQuestion(ExamRepository examRepository) {
        this.examRepository = examRepository;
    }

    @Override
    public Question map(QuestionInDTO in) {
        Question question = new Question();
        question.setQuestionText(in.getQuestionText());

        Exam exam = examRepository.findById(in.getExamId())
                .orElseThrow(() -> new IllegalStateException("Exam not found" + in.getExamId()));
        question.setExamQuestions(exam);
        question.setCreatedAtQuestion(LocalDateTime.now());
        return question;
    }
}

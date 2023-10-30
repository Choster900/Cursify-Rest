package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.ExamResult;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.ExamRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.ExamResultDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class ExamResultInDTOToExamResultDTO implements IMapper<ExamResultDTO, ExamResult>{

    private final ExamRepository examRepository;
    private final UserRepository userRepository;
    public ExamResultInDTOToExamResultDTO(ExamRepository examRepository, UserRepository userRepository) {
        this.examRepository = examRepository;
        this.userRepository = userRepository;
    }

    @Override
    public ExamResult map(ExamResultDTO in) {
        ExamResult examResult = new ExamResult();

        Exam exam = examRepository.findById(in.getExamId())
                .orElseThrow(() -> new IllegalArgumentException("Exam no found con ID: " + in.getExamId()));
        examResult.setExam(exam);

        User user = userRepository.findById(in.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User no found con ID: " + in.getUserId()));
        examResult.setUserExamResult(user);

        examResult.setResultScore(in.getResultScore());
        examResult.setResultGrade(in.getResultGrade());

        examResult.setCreatedAtResult(LocalDateTime.now());

        return examResult;
    }
}

package com.itca.cursify.service;

import com.itca.cursify.mapper.ExamResultInDTOToExamResultDTO;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.ExamResult;
import com.itca.cursify.persistece.repository.ExamResultRepository;
import com.itca.cursify.service.dto.ExamResultDTO;
import org.springframework.stereotype.Service;

@Service
public class ExamResultService {
    private final ExamResultRepository examResultRepository;
    private final ExamResultInDTOToExamResultDTO examResultInDTOToExamResultDTO;

    public ExamResultService(ExamResultRepository examResultRepository, ExamResultInDTOToExamResultDTO examResultInDTOToExamResultDTO) {
        this.examResultRepository = examResultRepository;
        this.examResultInDTOToExamResultDTO = examResultInDTOToExamResultDTO;
    }

    public ExamResult insertExamResult(ExamResultDTO examResultDTO){
        ExamResult examResult  = examResultInDTOToExamResultDTO.map(examResultDTO);
        return this.examResultRepository.save(examResult);
    }
}

package com.itca.cursify.service;

import com.itca.cursify.mapper.ExamInDTOToExam;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.repository.ExamRepository;
import com.itca.cursify.service.dto.ExamInDTO;
import org.springframework.stereotype.Service;

@Service

public class ExamService {
    private final ExamRepository examRepository;
    private final ExamInDTOToExam examInDTOToExam;
    public ExamService(ExamRepository examRepository, ExamInDTOToExam examInDTOToExam) {
        this.examRepository = examRepository;
        this.examInDTOToExam = examInDTOToExam;
    }

    public Exam createNewExam(ExamInDTO examInDTO) {
        Exam exam = this.examInDTOToExam.map(examInDTO);
        return this.examRepository.save(exam);
    }
}

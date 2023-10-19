package com.itca.cursify.service;

import com.itca.cursify.mapper.ExamInDTOToExam;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.ExamRepository;
import com.itca.cursify.service.dto.ExamInDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service

public class ExamService {
    private final ExamRepository examRepository;
    private final ExamInDTOToExam examInDTOToExam;

    private final CourseRepository courseRepository;
    public ExamService(ExamRepository examRepository, ExamInDTOToExam examInDTOToExam, CourseRepository courseRepository) {
        this.examRepository = examRepository;
        this.examInDTOToExam = examInDTOToExam;
        this.courseRepository = courseRepository;
    }

    public Exam createNewExam(ExamInDTO examInDTO) {
        Exam exam = this.examInDTOToExam.map(examInDTO);
        return this.examRepository.save(exam);
    }

    public Exam updateExam(Long examId, ExamInDTO examInDTO){
        Optional<Exam> examOptional = examRepository.findById(examId);

        if (examOptional.isPresent()) {
            Exam exam = examOptional.get();

            // Mapea los valores del examInDTO al examen existente
            exam.setExamTitle(examInDTO.getExamTitle());
            exam.setExamDuration(examInDTO.getExamDuration());

            Course course = courseRepository.findById(examInDTO.getCourseId())
                    .orElseThrow(() -> new IllegalStateException("Course not found" + examInDTO.getCourseId()));

            exam.setCourse(course); // Asegúrate de que este campo se actualice correctamente
            exam.setModifiedAtExam(LocalDateTime.now());

            return examRepository.save(exam);
        } else {
            throw new IllegalStateException("El examen no se encontró");
        }
    }

}

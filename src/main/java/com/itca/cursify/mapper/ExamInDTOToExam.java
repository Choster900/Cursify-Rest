package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Exam;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.service.dto.ExamInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExamInDTOToExam implements IMapper<ExamInDTO, Exam> {

    private final CourseRepository courseRepository;

    public ExamInDTOToExam(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Exam map(ExamInDTO in) {
        Exam exam = new Exam();
        Course course = courseRepository.findById(in.getCourseId())
                .orElseThrow(() -> new IllegalStateException("Course not found" + in.getCourseId()));
        exam.setCourse(course);
        exam.setExamTitle(in.getExamTitle());
        exam.setExamDuration(in.getExamDuration());
        exam.setCreatedAtExam(LocalDateTime.now());
        return exam;
    }
}

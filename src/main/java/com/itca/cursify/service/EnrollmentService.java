package com.itca.cursify.service;

import com.itca.cursify.mapper.EnrollmentInDTOToEnrollment;
import com.itca.cursify.persistece.entity.Enrollment;
import com.itca.cursify.persistece.repository.EnrollmentRepository;
import com.itca.cursify.service.dto.EnrollmentInDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentInDTOToEnrollment enrollmentInDTOToEnrollment;
    public EnrollmentService(EnrollmentRepository enrollmentRepository, EnrollmentInDTOToEnrollment enrollmentInDTOToEnrollment) {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentInDTOToEnrollment = enrollmentInDTOToEnrollment;
    }
    public Enrollment enrollUserInCourse(EnrollmentInDTO enrollmentInDTO){
        Enrollment enrollment = this.enrollmentInDTOToEnrollment.map(enrollmentInDTO);
        return this.enrollmentRepository.save(enrollment);
    }
    public Optional<Enrollment> getEnrollmentByUserIdAndCourseId(Long userId, Long courseId) {
        return enrollmentRepository.findByUserUserIdAndCourseCourseId(userId, courseId);
    }
    @Transactional
    public void  unenrollUserFromCourse(Long userId, Long courseId) {
        enrollmentRepository.deleteByUserUserIdAndCourseCourseId(userId, courseId);
    }
}

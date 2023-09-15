package com.itca.cursify.service;

import com.itca.cursify.mapper.EnrollmentInDTOToEnrollment;
import com.itca.cursify.persistece.entity.Enrollment;
import com.itca.cursify.persistece.repository.EnrollmentRepository;
import com.itca.cursify.service.dto.EnrollmentInDTO;
import org.springframework.stereotype.Service;

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
}

package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Enrollment;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.EnrollmentInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Component
public class EnrollmentInDTOToEnrollment implements IMapper<EnrollmentInDTO, Enrollment> {
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public EnrollmentInDTOToEnrollment(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }

    @Override
    public Enrollment map(EnrollmentInDTO in) {
        Enrollment enrollment = new Enrollment();

        User user = userRepository.findById(in.getUserId()).orElseThrow(() -> new IllegalStateException("User not found"));
        enrollment.setUser(user);//Insertando el usuario

        Course course = courseRepository.findById(in.getCourseId()).orElseThrow(() -> new IllegalStateException("Course not found"));
        enrollment.setCourse(course);//Insertando el curso al que se inscribio

        enrollment.setEnrollmentDate(LocalDate.now());
        enrollment.setCreatedAtEnrollment(LocalDateTime.now());

        return enrollment;
    }
}

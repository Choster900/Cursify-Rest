package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Enrollment;
import com.itca.cursify.service.EnrollmentService;
import com.itca.cursify.service.dto.EnrollmentInDTO;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/enrollments")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping
    public Enrollment registerUserForCourse(@RequestBody EnrollmentInDTO enrollmentInDTO){
        return this.enrollmentService.enrollUserInCourse(enrollmentInDTO);
    }
    @GetMapping("isEnroll/{userId}/{courseId}")
    public Optional<Enrollment> findEnrollByUserIdAndCourseId(@PathVariable Long userId, @PathVariable Long courseId){
        return this.enrollmentService.getEnrollmentByUserIdAndCourseId(userId,courseId);

    }
}

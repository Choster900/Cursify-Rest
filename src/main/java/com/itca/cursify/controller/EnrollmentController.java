package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Enrollment;
import com.itca.cursify.service.EnrollmentService;
import com.itca.cursify.service.dto.EnrollmentInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/enrollements")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }
    @PostMapping
    public Enrollment registerUserForCourse(@RequestBody EnrollmentInDTO enrollmentInDTO){
        return this.enrollmentService.enrollUserInCourse(enrollmentInDTO);
    }
}

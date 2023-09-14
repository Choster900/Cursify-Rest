package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.service.CourseService;
import com.itca.cursify.service.dto.CourseInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public Course createCourse(@RequestBody CourseInDTO courseInDTO){
        return this.courseService.createNewCourse(courseInDTO);
    }
}

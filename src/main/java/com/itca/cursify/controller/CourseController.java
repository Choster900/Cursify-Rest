package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.service.CourseService;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/courses")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @PostMapping
    public Course createCourse(
            @RequestParam("file") MultipartFile file,
            @RequestParam("coursePhoto") String coursePhoto,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("courseDescription") String courseDescription,
            @RequestParam("courseName") String courseName,
            @RequestParam("userId") Long userId
            ){

        CourseInDTO courseInDTO = new CourseInDTO();
        courseInDTO.setCoursePhoto(coursePhoto);
        courseInDTO.setFile(file);
        courseInDTO.setCategoryId(categoryId);
        courseInDTO.setCourseDescription(courseDescription);
        courseInDTO.setCourseName(courseName);
        courseInDTO.setUserId(userId);

        return this.courseService.createNewCourse(courseInDTO);
    }
    @GetMapping("/findCourseByIdWithDetails/{courseId}")
    public CourseWithDTO getCourseWithDetail(@PathVariable Long courseId){
        return this.courseService.getCourseWithDetailsById(courseId);
    }
}

package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.service.CourseService;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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
    @PutMapping("/{courseId}")
    public Course modifieCourse(
            @PathVariable Long courseId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String coursePhoto,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String courseDescription,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Long userId
    ){

        CourseInDTO courseInDTO = new CourseInDTO();
        courseInDTO.setCoursePhoto(coursePhoto);
        courseInDTO.setFile(file);
        courseInDTO.setCategoryId(categoryId);
        courseInDTO.setCourseDescription(courseDescription);
        courseInDTO.setCourseName(courseName);
        courseInDTO.setUserId(userId);

        return this.courseService.modifiCourse(courseId,courseInDTO);
    }
    @PutMapping("changeState/{courseId}")
    public Course changeStateCourse(Long courseId){
        return this.courseService.changeStateCourse(courseId);
    }

    @GetMapping("/findCourseByIdWithDetails/{courseId}")
    public CourseWithDTO getCourseWithDetail(@PathVariable Long courseId){
        return this.courseService.getCourseWithDetailsById(courseId);
    }

    @GetMapping("/findCourseByUserCreatorWithDetails/{userId}")
    public List<CourseWithDTO> getCourseWithDetailByUserCreator(@PathVariable Long userId){
        return this.courseService.getCoursesWithDetailsByCreateUser(userId);
    }
}

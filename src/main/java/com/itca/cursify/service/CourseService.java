package com.itca.cursify.service;

import com.itca.cursify.mapper.CourseInDTOToCourse;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.service.dto.CourseInDTO;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseInDTOToCourse courseInDTOToCourse;

    public CourseService(CourseRepository courseRepository, CourseInDTOToCourse courseInDTOToCourse) {
        this.courseRepository = courseRepository;
        this.courseInDTOToCourse = courseInDTOToCourse;
    }


    public Course createNewCourse(CourseInDTO courseInDTO) {
        Course newCourse = courseInDTOToCourse.map(courseInDTO);
        return this.courseRepository.save(newCourse);
    }
}

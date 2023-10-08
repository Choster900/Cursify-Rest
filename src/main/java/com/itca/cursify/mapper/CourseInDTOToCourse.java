package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.CategoryRepository;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.Storage.StorageService;
import com.itca.cursify.service.dto.CourseInDTO;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Component
public class CourseInDTOToCourse implements IMapper<CourseInDTO, Course> {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final UserRepository userRepository;

    private final StorageService storageService;
    private final HttpServletRequest request;

    public CourseInDTOToCourse(CategoryRepository categoryRepository, CourseRepository courseRepository, UserRepository userRepository, StorageService storageService, HttpServletRequest request) {
        this.categoryRepository = categoryRepository;
        this.courseRepository = courseRepository;
        this.userRepository = userRepository;
        this.storageService = storageService;
        this.request = request;
    }

    @Override
    public Course map(CourseInDTO in) {
        Course course = new Course();

        course.setCourseName(in.getCourseName());
        course.setCourseDescription(in.getCourseDescription());
        course.setCoursePhoto("/media/" + in.getCoursePhoto());

        course.setCoursePublished(Published.PRIVADO);

        Category category = categoryRepository.findById(in.getCategoryId())
                .orElseThrow(() -> new IllegalArgumentException("Category no encontrada con ID: " + in.getCategoryId()));
        course.setCategory(category);

        User user = userRepository.findById(in.getUserId())
                .orElseThrow(() -> new IllegalStateException("User no encontrada con ID: " + in.getUserId()));
        course.setCreatorUser(user);



       String path = storageService.store(in.getFile());
        String host = request.getRequestURL().toString().replace(request.getRequestURI(), "");

        course.setCreatedAtCourse(LocalDate.now());
        return course;
    }
}

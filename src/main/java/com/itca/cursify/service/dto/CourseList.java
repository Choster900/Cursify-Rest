package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CourseList {

        private Long courseId;
        private String courseName;
        private String courseDescription;
        private String coursePhoto;
        private Published coursePublished;
        private Category category;
        private User user;
        private LocalDateTime createdAtCourse;

}

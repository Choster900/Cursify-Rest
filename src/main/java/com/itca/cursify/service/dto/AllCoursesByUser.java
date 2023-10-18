package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.Role;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.Data;

import javax.persistence.Column;
import java.time.LocalDateTime;
import java.util.List;
@Data
public class AllCoursesByUser {
    //DTO to  index From Tutor's
    private Long userId;
    private String userName;
    private String userLastName;
    private String userEmail;
    private Role role;
    private List<allCoursesDTO> coursesByUser;

    @Data
    public static class allCoursesDTO {
        private Long courseId;
        private String courseName;
        private String courseDescription;
        private String coursePhoto;
        private Published coursePublished;
        private Category category;
        private User user;
        private LocalDateTime createdAtCourse;
    }

}

package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Course;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriesWithCourses {
    private Long categoryId;
    private String categoryName;
    private String categoryPhoto;
    private LocalDateTime createdAtCategory;
    private List<Course> courses;
}

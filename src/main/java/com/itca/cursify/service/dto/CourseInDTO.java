package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Category;
import lombok.*;


@Data
@Getter
@Setter
public class CourseInDTO {
    private String courseName;
    private String courseDescription;
    private String coursePhoto;
    private Long categoryId;
    private Long userId;
}

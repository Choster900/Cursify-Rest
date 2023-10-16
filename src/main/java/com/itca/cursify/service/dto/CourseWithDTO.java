package com.itca.cursify.service.dto;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
@Data
@Getter
@Setter
public class CourseWithDTO {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String coursePhoto;
    private Published coursePublished;
    private LocalDateTime createdAtCourse;
    private Category category; // Object Category
    private User user; // Object User
    private List<SectionDTO> sections;
    private List<ExamDTO> exams;

    @Data
    @Getter
    @Setter
    public static class SectionDTO {
        private Long sectionId;
        private Long courseId;
        private String sectionTitle;
        private Published sectionState;

        private List<SectionContentDTO> sectionContents;

        // Constructor, getters y setters
    }
    @Data
    @Getter
    @Setter
    public static class SectionContentDTO {
        private Long contentId;
        private Long sectionId;

        private String contentName;
        private String contentType;
        private String contentFileName;
        // Otros campos que quieras incluir

        // Constructor, getters y setters
    }

    @Data
    @Getter
    @Setter
    public static class ExamDTO{
        private Long examId;
        private Long courseId;
        private String examTitle;
        private Integer examDuration;
        private Published examStatus;
       // private LocalDateTime createdAtExam;

    }
}

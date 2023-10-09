package com.itca.cursify.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Data
@Getter
@Setter
public class CourseWithDTO {

    private Long courseId;
    private String courseName;
    private String courseDescription;
    private String coursePhoto;
    // Otros campos que quieras incluir

    private List<SectionDTO> sections;

    // Constructor, getters y setters
    @Data
    @Getter
    @Setter
    public static class SectionDTO {
        private Long sectionId;
        private String sectionTitle;
        // Otros campos que quieras incluir

        private List<SectionContentDTO> sectionContents;

        // Constructor, getters y setters
    }
    @Data
    @Getter
    @Setter
    public static class SectionContentDTO {
        private Long contentId;
        private String contentName;
        private String contentType;
        private String contentFileName;
        // Otros campos que quieras incluir

        // Constructor, getters y setters
    }

    // Otros getters y setters necesarios
}

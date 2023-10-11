package com.itca.cursify.service.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;
@Data
public class SectionContentDTO {
    private String contentName;
    private String contentType;
    private String contentFileName;
    private MultipartFile fileVideoContent;
    private Long sectionId;
}

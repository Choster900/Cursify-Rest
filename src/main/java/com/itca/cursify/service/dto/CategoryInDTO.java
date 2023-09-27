package com.itca.cursify.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Data
@Getter
@Setter
public class CategoryInDTO {
    private String categoryPhoto;
    private String categoryName;
    private MultipartFile file;
}

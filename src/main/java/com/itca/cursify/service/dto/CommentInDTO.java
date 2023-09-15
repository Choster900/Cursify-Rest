package com.itca.cursify.service.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CommentInDTO {
    private Long userId;
    private Long courseId;
    private String commentText;
}

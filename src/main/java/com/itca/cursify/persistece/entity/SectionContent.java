package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "section_content")
public class SectionContent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "content_id")
    private Long contentId;
    @Column(name = "content_name")
    private String contentName;
    @Column(name = "content_type")
    private String contentType;
    @Column(name = "content_file_name")
    private String contentFileName;

    @ManyToOne
    @JoinColumn(name = "section_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Section section;

    @Column(name = "created_at_content")
    private LocalDateTime createdAtContent;
    @Column(name = "modified_at_content")
    private LocalDateTime modifiedAtContent;
}

package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itca.cursify.persistece.entity.enums.Published;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "section")
public class Section {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "section_id")
    private Long sectionId;
    @Column(name = "section_title")
    private String sectionTitle;
    @Column(name = "section_state")
    private Published sectionState;

    @ManyToOne
    @JoinColumn(name = "course_id") // Nombre de la columna que contendrá la clave foránea en la tabla Task
    @JsonIgnore
    private Course course;

    @Column(name = "created_at_section")
    private LocalDateTime createdAtSection;
    @Column(name = "modified_at_section")
    private LocalDateTime modifiedAtSection;

    @OneToMany(mappedBy = "section", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnore
    private List<SectionContent> sectionContents = new ArrayList<>();

}

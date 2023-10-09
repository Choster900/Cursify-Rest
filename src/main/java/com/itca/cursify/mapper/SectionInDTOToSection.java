package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.service.dto.SectionInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class SectionInDTOToSection implements IMapper<SectionInDTO, Section>{
    private final CourseRepository courseRepository;

    public SectionInDTOToSection(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public Section map(SectionInDTO in) {
        Section section = new Section();
        section.setSectionTitle(in.getSectionTitle());
        Course course = courseRepository.findById(in.getCourseId())
                .orElseThrow(() -> new IllegalArgumentException("Category no encontrada con ID: " + in.getCourseId()));

        section.setCourse(course);
        section.setCreatedAtSection(LocalDateTime.now());
        return section;
    }
}

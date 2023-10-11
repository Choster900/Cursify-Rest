package com.itca.cursify.service;

import com.itca.cursify.mapper.CourseInDTOToCourse;
import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.Section;
import com.itca.cursify.persistece.entity.SectionContent;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseInDTOToCourse courseInDTOToCourse;

    public CourseService(CourseRepository courseRepository, CourseInDTOToCourse courseInDTOToCourse) {
        this.courseRepository = courseRepository;
        this.courseInDTOToCourse = courseInDTOToCourse;
    }


    public Course createNewCourse(CourseInDTO courseInDTO) {
        Course newCourse = courseInDTOToCourse.map(courseInDTO);
        return this.courseRepository.save(newCourse);
    }

    public Course modifiCourse(Long courseId, CourseInDTO courseInDTO){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.get();
        Category category = course.getCategory();
        Course updateCourse = courseInDTOToCourse.map(courseInDTO);
        //Starting to set information
        course.setCourseName(updateCourse.getCourseName());
        course.setCategory(category);
        course.setCourseDescription(updateCourse.getCourseDescription());
        course.setCoursePhoto(updateCourse.getCoursePhoto());
        course.setModifiedAtCourse(LocalDateTime.now());
        return this.courseRepository.save(course);
    }
    public Course changeStateCourse(Long courseId){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.get();

        Published currentState = course.getCoursePublished();//Getting SectionState
        Published newState = (currentState == Published.PRIVADO) ? Published.PUBLICADO : Published.PRIVADO;

        course.setCoursePublished(newState);
        course.setModifiedAtCourse(LocalDateTime.now());
        return courseRepository.save(course);
    }

    public CourseWithDTO getCourseWithDetailsById(Long courseId){
        Course course = courseRepository.findById(courseId).orElse(null);

        if (course != null) {
            CourseWithDTO courseDTO = new CourseWithDTO();
            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCoursePhoto(course.getCoursePhoto());

            // Mapea las secciones
            List<CourseWithDTO.SectionDTO> sectionDTOList = new ArrayList<>();
            for (Section section : course.getSections()){
                CourseWithDTO.SectionDTO sectionDTO = new CourseWithDTO.SectionDTO();
                sectionDTO.setSectionId(section.getSectionId());
                sectionDTO.setSectionTitle(section.getSectionTitle());

                // Mapea los contenidos de la sección
                List<CourseWithDTO.SectionContentDTO> sectionContentDTOList = new ArrayList<>();
                for (SectionContent content : section.getSectionContents()) {
                    CourseWithDTO.SectionContentDTO contentDTO = new CourseWithDTO.SectionContentDTO();
                    contentDTO.setContentId(content.getContentId());
                    contentDTO.setContentName(content.getContentName());
                    contentDTO.setContentType(content.getContentType());
                    contentDTO.setContentFileName(content.getContentFileName());
                    // Otros campos de contenido
                    sectionContentDTOList.add(contentDTO);
                }

                sectionDTO.setSectionContents(sectionContentDTOList);
                sectionDTOList.add(sectionDTO);
            }
            courseDTO.setSections(sectionDTOList);
            return courseDTO;
        }
        return null; // Manejo de caso en el que el curso no se encuentre
    }

}

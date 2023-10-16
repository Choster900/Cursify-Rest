package com.itca.cursify.service;

import com.itca.cursify.exceptions.ExceptionsCursify;
import com.itca.cursify.mapper.CourseInDTOToCourse;
import com.itca.cursify.persistece.entity.*;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    private final CourseRepository courseRepository;
    private final CourseInDTOToCourse courseInDTOToCourse;
    private final UserRepository userRepository;

    public CourseService(CourseRepository courseRepository, CourseInDTOToCourse courseInDTOToCourse, UserRepository userRepository) {
        this.courseRepository = courseRepository;
        this.courseInDTOToCourse = courseInDTOToCourse;
        this.userRepository = userRepository;
    }


    public Course createNewCourse(CourseInDTO courseInDTO) {
        Course newCourse = courseInDTOToCourse.map(courseInDTO);
        return this.courseRepository.save(newCourse);
    }

    public Course modifiCourse(Long courseId, CourseInDTO courseInDTO){
        Optional<Course> courseOptional = courseRepository.findById(courseId);
        Course course = courseOptional.get();
        Course updateCourse = courseInDTOToCourse.map(courseInDTO);
        //Starting to set information
        course.setCourseName(updateCourse.getCourseName());
        course.setCategory(updateCourse.getCategory());
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
            courseDTO.setCategory(course.getCategory());
            courseDTO.setCoursePublished(course.getCoursePublished());
            courseDTO.setUser(course.getCreatorUser());

            //Mapear los examenes
            List<CourseWithDTO.ExamDTO> examDTOList = new ArrayList<>();
            for (Exam exam : course.getExamArrayList()){
                CourseWithDTO.ExamDTO examDTO = new CourseWithDTO.ExamDTO();
                examDTO.setExamId(exam.getExamId());
                examDTO.setCourseId(course.getCourseId());
                examDTO.setExamTitle(exam.getExamTitle());
                examDTO.setExamDuration(exam.getExamDuration());
                examDTO.setExamStatus(exam.getExamStatus());
                examDTOList.add(examDTO);
            }
            courseDTO.setExams(examDTOList);

            // Mapea las secciones
            List<CourseWithDTO.SectionDTO> sectionDTOList = new ArrayList<>();
            for (Section section : course.getSections()){
                CourseWithDTO.SectionDTO sectionDTO = new CourseWithDTO.SectionDTO();
                sectionDTO.setSectionId(section.getSectionId());
                sectionDTO.setCourseId(course.getCourseId());
                sectionDTO.setSectionTitle(section.getSectionTitle());
                sectionDTO.setSectionState(section.getSectionState());
                // Mapea los contenidos de la sección
                List<CourseWithDTO.SectionContentDTO> sectionContentDTOList = new ArrayList<>();
                for (SectionContent content : section.getSectionContents()) {
                    CourseWithDTO.SectionContentDTO contentDTO = new CourseWithDTO.SectionContentDTO();
                    contentDTO.setContentId(content.getContentId());
                    contentDTO.setSectionId(section.getSectionId());
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
    public List<CourseWithDTO> getCoursesWithDetailsByCreateUser(Long userId){
        Optional<User> optionalUser =  this.userRepository.findByUserId(userId);
        if (optionalUser.isEmpty()){
            throw new ExceptionsCursify("User not found", HttpStatus.NOT_FOUND);
        }
        List<Course> courses = courseRepository.findAllByCreatorUserId(userId);
        List<CourseWithDTO> courseDTOList = new ArrayList<>();

        for (Course course : courses) {
            CourseWithDTO courseDTO = new CourseWithDTO();
            // Mapear los detalles del curso
            // ...
            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCoursePhoto(course.getCoursePhoto());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setCoursePublished(course.getCoursePublished());
            courseDTO.setUser(course.getCreatorUser());
            courseDTO.setCreatedAtCourse(course.getCreatedAtCourse());

            courseDTOList.add(courseDTO);
        }

        return courseDTOList;
    }



}

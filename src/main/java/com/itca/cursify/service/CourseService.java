package com.itca.cursify.service;

import com.itca.cursify.exceptions.ExceptionsCursify;
import com.itca.cursify.mapper.CourseInDTOToCourse;
import com.itca.cursify.persistece.entity.*;
import com.itca.cursify.persistece.entity.enums.Published;
import com.itca.cursify.persistece.repository.CategoryRepository;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.EnrollmentRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.AllCoursesByUser;
import com.itca.cursify.service.dto.CourseList;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.EntityManager;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    @PersistenceContext
    private EntityManager entityManager;
    private final CourseRepository courseRepository;
    private final CourseInDTOToCourse courseInDTOToCourse;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final EnrollmentRepository enrollmentRepository;
    public CourseService(EntityManager entityManager, CourseRepository courseRepository, CourseInDTOToCourse courseInDTOToCourse, UserRepository userRepository, CategoryRepository categoryRepository, EnrollmentRepository enrollmentRepository) {
        this.entityManager = entityManager;
        this.courseRepository = courseRepository;
        this.courseInDTOToCourse = courseInDTOToCourse;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.enrollmentRepository = enrollmentRepository;
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

            //Mapear los comments
            List<CourseWithDTO.CommentDTO> comments = new ArrayList<>();
            for (Comment comment : course.getComments()){
                CourseWithDTO.CommentDTO commentDTO = new CourseWithDTO.CommentDTO();
                commentDTO.setCommentId(comment.getCommentId());
                commentDTO.setCommentText(comment.getCommentText());
                commentDTO.setUser(comment.getUser());
                commentDTO.setCourse(comment.getCourse());
                courseDTO.setCreatedAtCourse(comment.getCreatedAtComment());

                comments.add(commentDTO);
            }
            courseDTO.setComments(comments);

            //Mapear los examenes
            List<CourseWithDTO.ExamDTO> examDTOList = new ArrayList<>();
            for (Exam exam : course.getExamArrayList()){
                CourseWithDTO.ExamDTO examDTO = new CourseWithDTO.ExamDTO();
                examDTO.setExamId(exam.getExamId());
                examDTO.setCourseId(course.getCourseId());
                examDTO.setExamTitle(exam.getExamTitle());
                examDTO.setExamDuration(exam.getExamDuration());
                examDTO.setExamStatus(exam.getExamStatus());

                List<CourseWithDTO.ExamResultInDTO> examResultInDTOS = new ArrayList<>();
                for (ExamResult result : exam.getExamResults()){
                    CourseWithDTO.ExamResultInDTO resultDTO = new CourseWithDTO.ExamResultInDTO();
                    resultDTO.setResultId(result.getResultId());
                    resultDTO.setUser(result.getUserExamResult());
                    resultDTO.setResultGrade(result.getResultGrade());
                    resultDTO.setResultScore(result.getResultScore());
                    resultDTO.setCreatedAtResult(result.getCreatedAtResult());
                    examResultInDTOS.add(resultDTO);
                }
                examDTO.setExamResultInDTOS(examResultInDTOS);
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

    public List<CourseWithDTO> getEnrolledCoursesWithDetails(Long userId){

        List<Enrollment> enrollments = enrollmentRepository.findByUserUserId(userId);
        List<CourseWithDTO> courseDTOList = new ArrayList<>();

        for (Enrollment enrollment : enrollments) {
            CourseWithDTO courseDTO = new CourseWithDTO();

            Course course = enrollment.getCourse();

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


    public List<AllCoursesByUser> getAllCoursesWithDetails(){
        List<User> users =  this.userRepository.findAll();
        List<AllCoursesByUser> completeQueryUsersWithCourses = new ArrayList<>();
        for (User user : users) {
            AllCoursesByUser allCourses = new AllCoursesByUser();
            allCourses.setUserId(user.getUserId());
            allCourses.setUserEmail(user.getUserEmail());
            allCourses.setUserName(user.getUserName());
            allCourses.setUserLastName(user.getUserLastName());
            allCourses.setRole(user.getRole());
            List<AllCoursesByUser.allCoursesDTO> allCoursesList = new ArrayList<>();
            for (Course course : user.getCoursesList()){
                AllCoursesByUser.allCoursesDTO allCoursesByUser = new AllCoursesByUser.allCoursesDTO();
                allCoursesByUser.setCourseId(course.getCourseId());
                allCoursesByUser.setCourseName(course.getCourseName());
                allCoursesByUser.setCoursePublished(course.getCoursePublished());
                allCoursesByUser.setCoursePhoto(course.getCoursePhoto());
                allCoursesByUser.setCoursePublished(course.getCoursePublished());
                allCoursesByUser.setCategory(course.getCategory());
                allCoursesByUser.setUser(course.getCreatorUser());
                allCoursesByUser.setCreatedAtCourse(course.getCreatedAtCourse());
                allCoursesList.add(allCoursesByUser);// Add course to arraylist
            }
            allCourses.setCoursesByUser(allCoursesList);
            completeQueryUsersWithCourses.add(allCourses);
        }
        return completeQueryUsersWithCourses;

    }

    //Get course by category


    public List<CourseList> getAllCoursesByCategory(Long categoryId) {
        Optional<Category> optionalCategory =  this.categoryRepository.findById(categoryId);
        if (optionalCategory.isEmpty()){
            throw new ExceptionsCursify("Category not found", HttpStatus.NOT_FOUND);
        }
        List<Course> courses = courseRepository.findAllByCategory(categoryId);
        List<CourseList> courseList = new ArrayList<>();

        for (Course course : courses) {
            CourseList courseDTO = new CourseList();

            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCoursePhoto(course.getCoursePhoto());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setCoursePublished(course.getCoursePublished());
            courseDTO.setUser(course.getCreatorUser());
            courseDTO.setCreatedAtCourse(course.getCreatedAtCourse());

            courseList.add(courseDTO);
        }

        return courseList;
    }

    public List<CourseList> getRandomCourses() {
        List<Course> courses = courseRepository.findRandomCourses();
        List<CourseList> courseList = new ArrayList<>();

        for (Course course : courses) {
            CourseList courseDTO = new CourseList();

            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCoursePhoto(course.getCoursePhoto());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setCoursePublished(course.getCoursePublished());
            courseDTO.setUser(course.getCreatorUser());
            courseDTO.setCreatedAtCourse(course.getCreatedAtCourse());

            courseList.add(courseDTO);
        }

        return courseList;
    }

    //
    public List<CourseList> getTrendingCourses() {
        EntityManager em = entityManager.getEntityManagerFactory().createEntityManager();

        String jpqlQuery = "SELECT c FROM Course c " +
                "JOIN Enrollment e ON c.courseId = e.course.courseId " +
                "GROUP BY c.courseId " +
                "ORDER BY COUNT(e.enrollmentId) DESC";

        TypedQuery<Course> query = em.createQuery(jpqlQuery, Course.class).setMaxResults(2);

        List<Course> courses = query.getResultList();
        List<CourseList> courseList = new ArrayList<>();

        for (Course course : courses) {
            CourseList courseDTO = new CourseList();

            courseDTO.setCourseId(course.getCourseId());
            courseDTO.setCourseName(course.getCourseName());
            courseDTO.setCourseDescription(course.getCourseDescription());
            courseDTO.setCoursePhoto(course.getCoursePhoto());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setCoursePublished(course.getCoursePublished());
            courseDTO.setUser(course.getCreatorUser());
            courseDTO.setCreatedAtCourse(course.getCreatedAtCourse());

            courseList.add(courseDTO);
        }

        em.close(); // ¡No olvides cerrar el EntityManager!

        return courseList;
    }








}

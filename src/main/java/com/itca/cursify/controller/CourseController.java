package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.service.CourseService;
import com.itca.cursify.service.dto.AllCoursesByUser;
import com.itca.cursify.service.dto.CourseList;
import com.itca.cursify.service.dto.CourseInDTO;
import com.itca.cursify.service.dto.CourseWithDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/v1/courses")
@Api(value = "Controlador de Ejemplo", description = "Operaciones relacionadas con Ejemplo")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }
    @GetMapping
    @ApiOperation(value = "Obtener los cursos por usuarios", notes = "Esta consulta obtiene todos los cursos por usuarios y sus detalles")
    public List<AllCoursesByUser> getAllCourseWithDetail(){
        return this.courseService.getAllCoursesWithDetails();
    }
    @PostMapping
    public Course createCourse(
            @RequestParam("file") MultipartFile file,
            @RequestParam("coursePhoto") String coursePhoto,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("courseDescription") String courseDescription,
            @RequestParam("courseName") String courseName,
            @RequestParam("userId") Long userId
            ){

        CourseInDTO courseInDTO = new CourseInDTO();
        courseInDTO.setCoursePhoto(coursePhoto);
        courseInDTO.setFile(file);
        courseInDTO.setCategoryId(categoryId);
        courseInDTO.setCourseDescription(courseDescription);
        courseInDTO.setCourseName(courseName);
        courseInDTO.setUserId(userId);

        return this.courseService.createNewCourse(courseInDTO);
    }
    @PutMapping("/{courseId}")
    @ApiOperation(value = "Obtener Ejemplo", notes = "Obtiene un ejemplo")
    public Course modifieCourse(
            @PathVariable Long courseId,
            @RequestParam(required = false) MultipartFile file,
            @RequestParam(required = false) String coursePhoto,
            @RequestParam(required = false) Long categoryId,
            @RequestParam(required = false) String courseDescription,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) Long userId
    ){

        CourseInDTO courseInDTO = new CourseInDTO();
        courseInDTO.setCoursePhoto(coursePhoto);
        courseInDTO.setFile(file);
        courseInDTO.setCategoryId(categoryId);
        courseInDTO.setCourseDescription(courseDescription);
        courseInDTO.setCourseName(courseName);
        courseInDTO.setUserId(userId);

        return this.courseService.modifiCourse(courseId,courseInDTO);
    }
    @PutMapping("changeState/{courseId}")
    public Course changeStateCourse(Long courseId){
        return this.courseService.changeStateCourse(courseId);
    }

    @GetMapping("/findCourseByIdWithDetails/{courseId}")
    @ApiOperation(value = "Obtener solamente un curso", notes = "Retorna el curso buscado por el courseId")
    public CourseWithDTO getCourseWithDetail(@PathVariable Long courseId){
        return this.courseService.getCourseWithDetailsById(courseId);
    }

    @GetMapping("/findCourseByUserCreatorWithDetails/{userId}")
    @ApiOperation(value = "Obtener todos los cursos filtrados por un solo usuario", notes = "Esta consulta se usa para cuando estamos filtrando los cursos que tenemos en nuestra posecion")
    public List<CourseWithDTO> getCourseWithDetailByUserCreator(@PathVariable Long userId){
        return this.courseService.getCoursesWithDetailsByCreateUser(userId);
    }
    @GetMapping("/findEnrolledCoursesWithDetails/{userId}")
    @ApiOperation(value = "Obtener todos los cursos donde el usuario este subscrito", notes = "Esta consulta se usa para mostrar los cursos donde el usuario que este logeado cuando vaya a ver sus cursos logeado aparescan los que tiene")
    public List<CourseWithDTO> getEnrolledCoursesWithDetails(@PathVariable Long userId){
        return this.courseService.getEnrolledCoursesWithDetails(userId);
    }
    @GetMapping("/trendingTwoCourses")
    @ApiOperation(value = "Obtener los cursos mas populares", notes = "Esta consulta retorna los seis cursos con más suscripciones")
    public List<CourseList> getOnlyTwoTrendingCourses(){
        return this.courseService.getTrendingCourses();
    }

    @GetMapping("/randomCourses")
    @ApiOperation(value = "Obtener couses aleatorios", notes = "Esta consulta retorna cursos aleatorios")
    public List<CourseList> getOnlySixRandomCourses(){
        return this.courseService.getRandomCourses();
    }

    @GetMapping("/finByCategory/{categoryId}")
    @ApiOperation(value = "Obtener todos los cursos filtrados por categoria", notes = "Esta consulta se usara cuando le den a una categoria y necesiten obtener todos los cursos filtrados por categoria")
    public List<CourseList> getAllCoursesByCategory(@PathVariable Long categoryId){
        return this.courseService.getAllCoursesByCategory(categoryId);
    }

}

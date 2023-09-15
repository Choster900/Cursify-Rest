package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Comment;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.entity.User;
import com.itca.cursify.persistece.repository.CourseRepository;
import com.itca.cursify.persistece.repository.UserRepository;
import com.itca.cursify.service.dto.CommentInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
@Component
public class CommentInDTOToComment implements IMapper<CommentInDTO, Comment> {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    public CommentInDTOToComment(UserRepository userRepository, CourseRepository courseRepository) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
    }


    @Override
    public Comment map(CommentInDTO in) {
        Comment comment = new Comment();
        User user = userRepository.findById(in.getUserId()).orElseThrow(() -> new IllegalStateException("User not found"));
        comment.setUser(user);
        Course course = courseRepository.findById(in.getCourseId()).orElseThrow(() -> new IllegalStateException("Course not found"));
        comment.setCourse(course);
        comment.setCommentText(in.getCommentText());
        comment.setCreatedAtComment(LocalDateTime.now());
        return comment;

    }
}

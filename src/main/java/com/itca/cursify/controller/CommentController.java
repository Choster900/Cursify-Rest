package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Comment;
import com.itca.cursify.service.CommentService;
import com.itca.cursify.service.dto.CommentInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/comments")
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping
    public Comment setCommentInCourse(@RequestBody CommentInDTO commentInDTO){
        return this.commentService.setCommentInCourse(commentInDTO);
    }

}

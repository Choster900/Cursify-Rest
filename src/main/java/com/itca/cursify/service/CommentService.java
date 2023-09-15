package com.itca.cursify.service;

import com.itca.cursify.mapper.CommentInDTOToComment;
import com.itca.cursify.persistece.entity.Comment;
import com.itca.cursify.persistece.repository.CommentRepository;
import com.itca.cursify.service.dto.CommentInDTO;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    private final CommentRepository commentRepository;
    private final CommentInDTOToComment commentInDTOToComment;
    public CommentService(CommentRepository commentRepository, CommentInDTOToComment commentInDTOToComment) {
        this.commentRepository = commentRepository;
        this.commentInDTOToComment = commentInDTOToComment;
    }
    public Comment setCommentInCourse(CommentInDTO commentInDTO){
        Comment comment = commentInDTOToComment.map(commentInDTO);
        return this.commentRepository.save(comment);

    }
}

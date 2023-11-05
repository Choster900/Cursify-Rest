package com.itca.cursify.persistece.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long commentId;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany(mappedBy = "comments", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Course> comments;


    @Column(name = "comment_text")
    private String commentText;
    @Column(name = "created_at_comment")
    private LocalDateTime createdAtComment;
    @Column(name = "modified_at_comment")
    private LocalDateTime modifiedAtComment;
}

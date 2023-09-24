package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    List<Question> findByExamQuestionsExamId(Long examId);

}

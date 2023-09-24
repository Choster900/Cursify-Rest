package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.AnswerOption;
import com.itca.cursify.service.AnswerOptionService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerOptionRepository extends JpaRepository<AnswerOption,Long> {
}

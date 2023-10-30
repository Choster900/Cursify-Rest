package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.ExamResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExamResultRepository extends JpaRepository<ExamResult,Long> {
}

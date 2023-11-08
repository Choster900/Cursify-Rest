package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment,Long> {
    Optional<Enrollment> findByUserUserIdAndCourseCourseId(Long userId, Long courseId);

    void deleteByUserUserIdAndCourseCourseId(Long userId, Long courseId);

    List<Enrollment> findByUserUserId(Long userId);


}

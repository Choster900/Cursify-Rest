package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query("SELECT c FROM Course c WHERE c.creatorUser.userId = :userId")
    List<Course> findAllByCreatorUserId(@Param("userId") Long userId);


}

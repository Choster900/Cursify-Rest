package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course,Long> {
}

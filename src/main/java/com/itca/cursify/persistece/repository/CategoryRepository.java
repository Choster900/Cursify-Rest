package com.itca.cursify.persistece.repository;

import com.itca.cursify.persistece.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    List<Category> findAllByOrderByCreatedAtCategoryAsc(); // Ordenar por fecha de creación ascendente
    List<Category> findAllByOrderByCreatedAtCategoryDesc(); // Ordenar por fecha de creación descendente

}

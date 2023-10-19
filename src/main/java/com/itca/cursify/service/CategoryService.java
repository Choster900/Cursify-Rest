package com.itca.cursify.service;

import com.itca.cursify.mapper.CategoryInDTOToCategory;
import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.entity.Course;
import com.itca.cursify.persistece.repository.CategoryRepository;
import com.itca.cursify.service.dto.CategoriesWithCourses;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryInDTOToCategory categoryInDTOToCategory;

    public CategoryService(CategoryRepository categoryRepository, CategoryInDTOToCategory categoryInDTOToCategory) {
        this.categoryRepository = categoryRepository;
        this.categoryInDTOToCategory = categoryInDTOToCategory;
    }
    public Category createNewCategory(CategoryInDTO categoryInDTO){
        Category category = categoryInDTOToCategory.map(categoryInDTO);
        return this.categoryRepository.save(category);
    }
    public Category modifyCategory(Long categoryId, CategoryInDTO categoryInDTO) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
            Category existingCategory = categoryOptional.get();
            Category updatedCategory = categoryInDTOToCategory.map(categoryInDTO);
            existingCategory.setCategoryName(updatedCategory.getCategoryName());
            existingCategory.setCategoryPhoto(updatedCategory.getCategoryPhoto());
            existingCategory.setModifiedAtCategory(LocalDateTime.now());
            return categoryRepository.save(existingCategory);
    }

    public List<CategoriesWithCourses> findAllCategories() {
        List<Category> categories = categoryRepository.findAllByOrderByCreatedAtCategoryDesc();
        List<CategoriesWithCourses> categoriesWithCourses = new ArrayList<>();

        for (Category category : categories) {
            List<Course> courses = category.getCourses();
            CategoriesWithCourses categoriesWithCoursesDTO = new CategoriesWithCourses();
            categoriesWithCoursesDTO.setCategoryId(category.getCategoryId());
            categoriesWithCoursesDTO.setCategoryName(category.getCategoryName());
            categoriesWithCoursesDTO.setCategoryPhoto(category.getCategoryPhoto());
            categoriesWithCoursesDTO.setCreatedAtCategory(category.getCreatedAtCategory());
            categoriesWithCoursesDTO.setCourses(courses);

            categoriesWithCourses.add(categoriesWithCoursesDTO);
        }

        return categoriesWithCourses;
    }


}

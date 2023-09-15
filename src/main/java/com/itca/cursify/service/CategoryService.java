package com.itca.cursify.service;

import com.itca.cursify.mapper.CategoryInDTOToCategory;
import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.persistece.repository.CategoryRepository;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.stereotype.Service;

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
}

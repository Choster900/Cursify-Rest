package com.itca.cursify.mapper;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Component
public class CategoryInDTOToCategory implements IMapper<CategoryInDTO, Category> {
    @Override
    public Category map(CategoryInDTO in) {
        Category category = new Category();
        category.setCategoryName(in.getCategoryName());
        category.setCategoryPhoto(in.getCategoryPhoto());
        category.setCreatedAtCategory(LocalDate.now());
        return category;
    }
}

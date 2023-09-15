package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.service.CategoryService;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public Category createCategory(@RequestBody CategoryInDTO categoryInDTO){
        return this.categoryService.createNewCategory(categoryInDTO);
    }
}

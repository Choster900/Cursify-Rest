package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.service.CategoryService;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/v1/categories")
@CrossOrigin("*")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @PostMapping
    public Category createCategory(@RequestParam("file") MultipartFile file, @RequestParam("categoryPhoto") String categoryPhoto,
                                   @RequestParam("categoryName") String categoryName) {
        CategoryInDTO categoryInDTO = new CategoryInDTO();
        categoryInDTO.setCategoryName(categoryName);
        categoryInDTO.setCategoryPhoto(categoryPhoto);
        categoryInDTO.setFile(file);

        return this.categoryService.createNewCategory(categoryInDTO);
    }

}

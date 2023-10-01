package com.itca.cursify.controller;

import com.itca.cursify.persistece.entity.Category;
import com.itca.cursify.service.CategoryService;
import com.itca.cursify.service.dto.CategoriesWithCourses;
import com.itca.cursify.service.dto.CategoryInDTO;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

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

    @GetMapping("/findAllCategories/")
    public List<CategoriesWithCourses> categoriesWithCourses(){
        return this.categoryService.findAllCategories();
    }

    @PutMapping("/{id}")
    public Category updateCategory(@PathVariable Long id,
                                   @RequestParam(required = false) MultipartFile file,
                                   @RequestParam(required = false) String categoryPhoto,
                                   @RequestParam(required = false) String categoryName) {
        CategoryInDTO categoryInDTO = new CategoryInDTO();
        categoryInDTO.setCategoryName(categoryName);
        categoryInDTO.setCategoryPhoto(categoryPhoto);
        categoryInDTO.setFile(file);
        return categoryService.modifyCategory(id, categoryInDTO);
    }


}

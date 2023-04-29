package com.abhiprojects.blog.blogappapis.controllers;

import com.abhiprojects.blog.blogappapis.payloads.CategoryDto;
import com.abhiprojects.blog.blogappapis.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto)
    {
        CategoryDto cat=this.categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(cat, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId)
    {
        CategoryDto updatedCategory=this.categoryService.updateCategory(categoryDto,categoryId);
        return new ResponseEntity<>(updatedCategory,HttpStatus.CREATED);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<?> deleteCategoryById(@PathVariable Integer categoryId)
    {
        this.categoryService.deleteCategoryById(categoryId);
        return ResponseEntity.ok(Map.of("message","category successfully deleted"));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId)
    {
        CategoryDto cat=this.categoryService.getCategory(categoryId);
        return new ResponseEntity<>(cat,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategories()
    {
        List<CategoryDto> categoryDtoList=this.categoryService.getAllCatogories();
        return new ResponseEntity<>(categoryDtoList,HttpStatus.OK);
    }
}

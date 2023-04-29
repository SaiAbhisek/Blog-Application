package com.abhiprojects.blog.blogappapis.services;

import com.abhiprojects.blog.blogappapis.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {

    //create
    CategoryDto createCategory(CategoryDto categoryDto);

    //update
    CategoryDto updateCategory(CategoryDto categoryDto,Integer categoryId);

    //delete
    void deleteCategoryById(Integer categoryId);

    //get
    CategoryDto getCategory(Integer categoryId);

    //getAll
    List<CategoryDto> getAllCatogories();

}

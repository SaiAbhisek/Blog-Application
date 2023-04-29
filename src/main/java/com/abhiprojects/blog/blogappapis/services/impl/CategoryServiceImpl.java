package com.abhiprojects.blog.blogappapis.services.impl;

import com.abhiprojects.blog.blogappapis.entities.Category;
import com.abhiprojects.blog.blogappapis.exceptions.ResourceNotFoundException;
import com.abhiprojects.blog.blogappapis.payloads.CategoryDto;
import com.abhiprojects.blog.blogappapis.repositories.CategoryRepo;
import com.abhiprojects.blog.blogappapis.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category cat=this.modelMapper.map(categoryDto, Category.class);
        Category savedCat=this.categoryRepo.save(cat);
        return this.modelMapper.map(savedCat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category not Found"));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category cat1=this.categoryRepo.save(cat);
        return this.modelMapper.map(cat1, CategoryDto.class);
    }

    @Override
    public void deleteCategoryById(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not found"));
        this.categoryRepo.delete(cat);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {
        Category cat=this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category Not Found"));

        return this.modelMapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCatogories() {
        List<Category> categories=this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList=categories.stream().map((catogory)->this.modelMapper.map(catogory, CategoryDto.class)).collect(Collectors.toList());

        return categoryDtoList;
    }
}

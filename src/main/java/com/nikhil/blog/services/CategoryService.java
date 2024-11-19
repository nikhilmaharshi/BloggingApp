package com.nikhil.blog.services;

import com.nikhil.blog.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto request);
    CategoryDto updateCategory(CategoryDto request, Integer categoryId);
    CategoryDto getCategoryById(Integer categoryId);
    void deleteCategory(Integer categoryId);
    List<CategoryDto> getCategories();
}

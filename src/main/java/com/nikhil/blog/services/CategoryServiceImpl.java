package com.nikhil.blog.services;

import com.nikhil.blog.dto.CategoryDto;
import com.nikhil.blog.entities.Category;
import com.nikhil.blog.exceptions.ResourceNotFoundException;
import com.nikhil.blog.repositories.CategoryRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService{
    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CategoryDto createCategory(CategoryDto request) {
        Category category = categoryRepo.save(dtoToCategory(request));
        return categoryToDto(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto request, Integer categoryId) {
        Category existingCategory = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        existingCategory.setCategoryTitle(request.getCategoryTitle());
        existingCategory.setCategoryDescription(request.getCategoryDescription());

        Category updatedCategory = categoryRepo.save(existingCategory);

        return categoryToDto(updatedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));

        return categoryToDto(category);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "Id", categoryId));
        categoryRepo.delete(category);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categorieDtos = categoryRepo.findAll().stream().map(this::categoryToDto).toList();
        return categorieDtos;
    }

    private Category dtoToCategory(CategoryDto dto){
        return modelMapper.map(dto,Category.class);
    }
    private CategoryDto categoryToDto(Category category){
        return modelMapper.map(category,CategoryDto.class);
    }
}

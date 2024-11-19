package com.nikhil.blog.controllers;

import com.nikhil.blog.dto.CategoryDto;
import com.nikhil.blog.services.CategoryService;
import com.nikhil.blog.utils.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Validated
@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //Post request
    @PostMapping("/create")  // /users is the API endpoint for creating a new user
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto request){
        CategoryDto categoryDto = categoryService.createCategory(request);
        return new ResponseEntity<>(categoryDto, HttpStatus.CREATED);
    }

    //Put request
    @PutMapping("/update/{categoryId}")  // /update/{userId} is the API endpoint for updating a user by id
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto request, @PathVariable Integer categoryId){
        CategoryDto categoryDto = categoryService.updateCategory(request, categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }

    //Get request by id
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Integer categoryId){
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    //Get request all users
    @GetMapping("/all")
    public ResponseEntity<List<CategoryDto>> getAllCategories(){
        List<CategoryDto> categoryDto = categoryService.getCategories();
        return new ResponseEntity<>(categoryDto, HttpStatus.OK);
    }
    //Delete request by id
    @DeleteMapping("/delete/{categoryId}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Integer categoryId){
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("Category Deleted Successfully!!",true),HttpStatus.OK);
    }

}

package com.smartcart.productservice.controllers;

import com.smartcart.productservice.dtos.categories.CategoryRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryResponseDto;
import com.smartcart.productservice.dtos.categories.CategoryStatusRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryTreeDto;
import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.mappers.CategoryMapper;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.services.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private CategoryService categoryService;
    private CategoryMapper  categoryMapper;

    public CategoryController(CategoryService categoryService, CategoryMapper categoryMapper) {
        this.categoryService = categoryService;
        this.categoryMapper = categoryMapper;
    }

    @PostMapping()
    public CategoryResponseDto addCategory(@RequestBody CategoryRequestDto categoryRequestDto){
        Category category=categoryService.addCategory(categoryRequestDto);

        return categoryMapper.toDto(category);
    }

    @GetMapping("/{id}")
    public CategoryResponseDto getCategoryById(@PathVariable("id") Long id) throws CategoryNotFoundException {
        Category category=categoryService.getCategoryById(id);
        return categoryMapper.toDto(category);
    }

    @GetMapping("/root")
    public List<CategoryResponseDto> getAllRootCategories(){
        List<Category> categoryList=categoryService.getAllRootCategories();
        List<CategoryResponseDto> categoryResponseDtoList=new ArrayList<>();
        for(Category category:categoryList){
            categoryResponseDtoList.add(categoryMapper.toDto(category));
        }
        return categoryResponseDtoList;
    }

    @GetMapping("/{parentId}/children")
    public List<CategoryResponseDto> getChildCategories(@PathVariable("parentId") Long parentId){
        List<Category> categoryList=categoryService.getChildCategories(parentId);
        List<CategoryResponseDto> categoryResponseDtoList=new ArrayList<>();
        for(Category category:categoryList){
            categoryResponseDtoList.add(categoryMapper.toDto(category));
        }
        return categoryResponseDtoList;
    }

    @GetMapping("/tree")
    public List<CategoryTreeDto> getFullCategoryTree(){
        return categoryService.getCategoryTree();
    }

    @GetMapping("/search")
    public CategoryResponseDto getCategoryBytitle(@RequestParam("title") String title) throws CategoryNotFoundException {
        Category category= categoryService.getCategoryBytitle(title);
        return  categoryMapper.toDto(category);
    }

    @PutMapping("/{id}")
    public CategoryResponseDto updateCategory(@PathVariable("id") Long id, @RequestBody CategoryRequestDto categoryRequestDto) throws CategoryNotFoundException {

        Category category= categoryService.updateCategory(id,categoryRequestDto);
         return categoryMapper.toDto(category);
    }

    @PatchMapping("/{id}/status")
    public CategoryResponseDto updateCategoryStatus(@PathVariable("id") Long id, @RequestBody CategoryStatusRequestDto categoryStatusRequestDto) {
        Category category=categoryService.updateCategoryStatus(id,categoryStatusRequestDto);
        return categoryMapper.toDto(category);
    }

    @DeleteMapping()
    public CategoryResponseDto deleteCategory(@PathVariable("id") Long id) throws CategoryNotFoundException {
        Category category=categoryService.deleteCategory(id);
        return categoryMapper.toDto(category);
    }

}

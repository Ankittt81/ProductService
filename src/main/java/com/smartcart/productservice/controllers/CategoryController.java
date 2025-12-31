package com.smartcart.productservice.controllers;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.services.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {
    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @GetMapping("/id/{id}")
    public Category getCategoryById(@PathVariable("id") Long id) throws CategoryNotFoundException {
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/name/{name}")
    public Category getCategoryByName(@PathVariable("name") String name) throws CategoryNotFoundException {
        return categoryService.getCategoryBytitle(name);
    }

    @PostMapping()
    public Category addCategory(@RequestBody Category category){
        System.out.println("Category ID = " + category.getId());
        return categoryService.addCategory(category);
    }

    @PutMapping()
    public Category updateCategory(@RequestBody Category category) throws CategoryNotFoundException {

        return categoryService.updateCategory(category);
    }

    @DeleteMapping("/{id}")
    public void deleteCategoryById(@PathVariable("id") Long id) throws CategoryNotFoundException {
        categoryService.deleteCategory(id);
    }

}

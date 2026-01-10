package com.smartcart.productservice.services;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Long id) throws CategoryNotFoundException;
    Category getCategoryBytitle(String title) throws CategoryNotFoundException;
    Category addCategory(Category category);
    Category updateCategory(Category category) throws CategoryNotFoundException;
    void deleteCategory(Long id) throws CategoryNotFoundException;
}

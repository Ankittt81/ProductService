package com.smartcart.productservice.services;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id) throws CategoryNotFoundException;
    public Category getCategoryBytitle(String title) throws CategoryNotFoundException;
    public Category addCategory(Category category);
    public Category updateCategory(Category category) throws CategoryNotFoundException;
    public void deleteCategory(Long id) throws CategoryNotFoundException;
}

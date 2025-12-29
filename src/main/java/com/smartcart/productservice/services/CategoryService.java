package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> getAllCategories();
    public Category getCategoryById(Long id);
    public Category getCategoryBytitle(String title);
    public Category addCategory(Category category);
    public Category updateCategory(Category category);
    public void deleteCategory(Long id);
}

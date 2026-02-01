package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.categories.CategoryRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryStatusRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryTreeDto;
import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.models.Category;

import java.util.List;

public interface CategoryService {
    Category addCategory(CategoryRequestDto categoryRequestDto);
    Category getCategoryById(Long id) throws CategoryNotFoundException; //At product creation needs validation
    List<Category> getAllRootCategories();
    List<Category> getChildCategories(Long parentId);
    List<CategoryTreeDto>  getCategoryTree();
    Category updateCategory(Long id, CategoryRequestDto categoryRequestDto);
    Category updateCategoryStatus(Long id, CategoryStatusRequestDto dto);
    Category deleteCategory(Long id) throws CategoryNotFoundException;

    Category getCategoryBytitle(String title) throws CategoryNotFoundException;

    //for productservice
    boolean hasChildren(Category category);
    public List<Category> getLeafCategories(Category category);


}

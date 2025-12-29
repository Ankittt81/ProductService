package com.smartcart.productservice.services;

import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("SelfCategoryService")
public class SelfCategoryService implements CategoryService {
    private CategoryRepository  categoryRepository;

    public SelfCategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public Category getCategoryBytitle(String title) {
        Optional<Category> category=categoryRepository.findByTitle(title);
        if(category.isEmpty()){
            return null;
        }
        return category.get();
    }

    @Override
    public Category addCategory(Category category) {
        if(category.getId()!=null){
            return null;
        }
        Optional<Category> optionalCategory=categoryRepository.findByTitle(category.getTitle());
        if(optionalCategory.isPresent()){
            System.out.println("Category already exists!");
            return null;
        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Optional<Category> optionalCategory=categoryRepository.findById(category.getId());
        if(optionalCategory.isEmpty()){
            return null;
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            System.out.println("Category not found");
            return;
        }
        categoryRepository.delete(optionalCategory.get());
        System.out.println("Category deleted");
    }
}

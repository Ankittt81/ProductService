package com.smartcart.productservice.services;

import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.exceptions.ResourceAlreadyExistsException;
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
    public Category getCategoryById(Long id) throws CategoryNotFoundException{
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CategoryNotFoundException(id);
        }
        return category.get();
    }

    @Override
    public Category getCategoryBytitle(String title) throws CategoryNotFoundException{
        Optional<Category> category=categoryRepository.findByTitle(title);
        if(category.isEmpty()){
            throw new  CategoryNotFoundException(title);
        }
        return category.get();
    }

    @Override
    public Category addCategory(Category category){
        if(category.getId()!=null){
            throw new IllegalArgumentException("Id should not be provided while creating new category");
        }
        Optional<Category> optionalCategory=categoryRepository.findByTitle(category.getTitle());
        if(optionalCategory.isPresent()){
            throw new ResourceAlreadyExistsException("Category already exists with title: " + category.getTitle());

        }
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category) throws CategoryNotFoundException{
        Optional<Category> optionalCategory=categoryRepository.findById(category.getId());
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException(category.getId());
        }
        Optional<Category> categoryName=categoryRepository.findByTitle(category.getTitle());
        if(categoryName.isPresent()){
            throw  new ResourceAlreadyExistsException("Category already exists with title: " + category.getTitle());
        }
        return categoryRepository.save(category);
    }

    @Override
    public void deleteCategory(Long id) throws CategoryNotFoundException{
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException(id);
        }
        categoryRepository.delete(optionalCategory.get());
        System.out.println("Category deleted");
    }
}

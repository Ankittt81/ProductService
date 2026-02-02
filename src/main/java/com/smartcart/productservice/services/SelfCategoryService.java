package com.smartcart.productservice.services;

import com.smartcart.productservice.dtos.categories.CategoryRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryStatusRequestDto;
import com.smartcart.productservice.dtos.categories.CategoryTreeDto;
import com.smartcart.productservice.exceptions.CategoryNotFoundException;
import com.smartcart.productservice.exceptions.ResourceAlreadyExistsException;
import com.smartcart.productservice.mappers.CategoryMapper;
import com.smartcart.productservice.models.Category;
import com.smartcart.productservice.models.Status;
import com.smartcart.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("SelfCategoryService")
public class SelfCategoryService implements CategoryService {
    private CategoryRepository  categoryRepository;
    private CategoryMapper  categoryMapper;

    public SelfCategoryService(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public Category addCategory(CategoryRequestDto Dto){
        Category parent=null;
        if(Dto.getParentId()!=null){
            Optional<Category> parentOptional=categoryRepository.findById(Dto.getParentId());
            if(parentOptional.isEmpty()){
                throw new CategoryNotFoundException("Category with"+Dto.getParentId()+"Id is not exist");
            }
            parent=parentOptional.get();
        }
        String normalizedTitle= Dto.getTitle().trim().toLowerCase();
        boolean exist=categoryRepository.existsByParentAndTitle(parent,normalizedTitle);
        if(exist){
            throw new ResourceAlreadyExistsException("Category with"+Dto.getTitle()+"Title already exists with given parent");
        }
        Dto.setTitle(normalizedTitle);
        Category category=categoryMapper.toEntity(Dto,parent);
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategoryById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()){
            throw new CategoryNotFoundException(id+" does not exist");
        }
        return category.get();
    }

    @Override
    public List<Category> getAllRootCategories() {
        return categoryRepository.findByParentIsNullAndStatus(Status.ACTIVE);
    }

    @Override
    public List<Category> getChildCategories(Long parentId) {
        Optional<Category> parentOptional=categoryRepository.findById(parentId);
        if(parentOptional.isEmpty()){
            throw new CategoryNotFoundException("Category with"+parentId+"Id does not exist");
        }
        Category parent=parentOptional.get();
        List<Category> childCategories=categoryRepository.findByParentAndStatus(parent,Status.ACTIVE);
        return childCategories;
    }

    @Override
    public List<CategoryTreeDto> getCategoryTree() {
        List<Category> categories=categoryRepository.findAllByStatus(Status.ACTIVE);
        Map<Long,CategoryTreeDto> map=new HashMap<>();
        for(Category category:categories){
            CategoryTreeDto Dto=new CategoryTreeDto();
            Dto.setId(category.getId());
            Dto.setTitle(category.getTitle());
            map.put(category.getId(),Dto);
        }

        List<CategoryTreeDto> roots=new ArrayList<>();

        for(Category category:categories){
            CategoryTreeDto current=map.get(category.getId());
            if(category.getParent()==null){
                roots.add(current);
            }else{
                CategoryTreeDto parent=map.get(category.getParent().getId());
                parent.getChildren().add(current);
            }
        }
        return roots;
    }

    @Override
    public Category getCategoryBytitle(String title){
        Optional<Category> category=categoryRepository.findByTitle(title);
        if(category.isEmpty()){
            throw new  CategoryNotFoundException(title+" does not exist");
        }
        return category.get();
    }

    @Override
    public Category updateCategory(Long id,CategoryRequestDto dto){
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
           throw new CategoryNotFoundException(id+" does not exist");

        }
       Category category=optionalCategory.get();
        String normalizedTitle= dto.getTitle().trim().toLowerCase();
        boolean exist=categoryRepository.existsByParentAndTitle(category.getParent(),normalizedTitle);
        if(exist){
            throw new ResourceAlreadyExistsException("Category with"+dto.getTitle()+"Title already exists with given parent");
        }
        category.setTitle(normalizedTitle);
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategoryStatus(Long id, CategoryStatusRequestDto dto) {
        Optional<Category> optionalCategory=categoryRepository.findById(id);
        if(optionalCategory.isEmpty()){
            throw new CategoryNotFoundException(id+" does not exist");
        }
        Category category=optionalCategory.get();
        category.setStatus(dto.getStatus());
        return categoryRepository.save(category);
    }

    @Override
    public Category deleteCategory(Long id){
        Optional<Category> categoryOptional=categoryRepository.findById(id);
        if(categoryOptional.isEmpty()){
            throw new  CategoryNotFoundException(id);
        }
        Category category=categoryOptional.get();
        category.setStatus(Status.DELETED);
        return categoryRepository.save(category);
    }

    //Products Requirement methods

    @Override
    public boolean hasChildren(Category category) {
        boolean exist=categoryRepository.existsByParent(category);
        return  exist;
    }

    public List<Category> getLeafCategories(Category category){
        List<Category> leafCategories=new ArrayList<>();
        collectLeafCategories(category,leafCategories);
        return leafCategories;
    }

    void collectLeafCategories(Category category,List<Category> leafCategories){
        // Fetch ACTIVE children of current category
        List<Category> children=categoryRepository.findAllByParentAndStatus(category,Status.ACTIVE);

        // If no children → this is a LEAF category
        if(children.isEmpty()){
            leafCategories.add(category);
            return;
        }
        // Otherwise, go deeper
        for(Category child:children){
            collectLeafCategories(child,leafCategories);
        }
    }
}

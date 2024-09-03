package com.example.Academy.service;

import com.example.Academy.Entity.Category;
import com.example.Academy.Repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class categoryServiceImpl implements CategoryService{

    @Autowired
    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Optional<Category> updateById(Long id,Category updatedCategory) {
        Optional<Category> existingCategory = categoryRepository.findById(id);
        if(existingCategory.isPresent()) {
            Category categoryToUpdate = existingCategory.get();
            categoryToUpdate.setName(updatedCategory.getName());
            categoryToUpdate.setDescription(updatedCategory.getDescription());

            if(updatedCategory.getBooks() != null) {
                categoryToUpdate.setBooks(updatedCategory.getBooks());
            }
            categoryRepository.save(categoryToUpdate);
            return Optional.of(categoryToUpdate);
        } else  {
            return Optional.empty();
        }
    }



    @Override
    public Optional<Category> getById(Long id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Category saveCategory(Category category) {
        return categoryRepository.save(category);
    }



}

package com.example.Academy.service;

import com.example.Academy.Entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CategoryService {
    List<Category> getAllCategory();
    void deleteById(Long id);

    Optional<Category> getById(Long id);
    Category saveCategory(Category category);
    Optional<Category> updateById(Long id,Category updatedCategory);
}

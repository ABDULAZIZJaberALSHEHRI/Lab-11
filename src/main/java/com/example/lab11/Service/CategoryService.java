package com.example.lab11.Service;

import com.example.lab11.Api.ApiException;
import com.example.lab11.Model.Category;
import com.example.lab11.Repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryService {
    private CategoryRepository categoryRepository;


    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    public void addCategory(Category category) {
        categoryRepository.save(category);
    }

    public void deleteCategory(int category_id) {
        Category category = categoryRepository.searchCategoryByID(category_id);
        if(category == null) {
            throw new ApiException("category not found");
        }
        categoryRepository.delete(category);
    }

    public void updateCategory(int category_id, Category category) {
        Category category1 = categoryRepository.searchCategoryByID(category_id);
        if(category1 == null) {
            throw new ApiException("category not found");
        }
        category1.setName(category.getName());
        categoryRepository.save(category1);
    }

    public Category searchCategoryByName(String name) {
        Category c = categoryRepository.searchCategoryByName(name);

        if(c == null) {
            throw new ApiException("category not found");
        }
        return c;
    }

}
